package kr.hhplus.be.server.application.reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.reservation.dto.PayReservationCommand;
import kr.hhplus.be.server.application.reservation.dto.PayResult;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.concert.repository.SeatRepository;
import kr.hhplus.be.server.domain.payment.Payment;
import kr.hhplus.be.server.domain.payment.PaymentStatus;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
import kr.hhplus.be.server.domain.point.UserPoint;
import kr.hhplus.be.server.domain.point.repository.UserPointRepository;
import kr.hhplus.be.server.domain.reservation.Reservation;
import kr.hhplus.be.server.domain.reservation.ReservationStatus;
import kr.hhplus.be.server.domain.reservation.repository.ReservationRepository;

@Service
public class PayReservationService implements PayReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final UserPointRepository userPointRepository;
    private final PaymentRepository paymentRepository;
    private final SeatRepository seatRepository;

    public PayReservationService(
            ReservationRepository reservationRepository,
            UserPointRepository userPointRepository,
            PaymentRepository paymentRepository,
            SeatRepository seatRepository) {
        this.reservationRepository = reservationRepository;
        this.userPointRepository = userPointRepository;
        this.paymentRepository = paymentRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional
    public PayResult pay(PayReservationCommand command) {

        Reservation reservation = reservationRepository.findById(command.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.getStatus() != ReservationStatus.HOLD) {
            throw new IllegalStateException("Reservation is not in HOLD state");
        }

        Seat seat = seatRepository.findById(reservation.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        UserPoint userPoint = userPointRepository.findByUserId(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("UserPoint not found"));

        userPoint.use(seat.getPrice());
        userPointRepository.save(userPoint);

        reservation.confirm();
        reservationRepository.save(reservation);

        Payment payment = new Payment(
                null,
                reservation.getReservationId(),
                seat.getPrice(),
                PaymentStatus.SUCCESS,
                java.time.LocalDateTime.now()
        );
        Payment savedPayment = paymentRepository.save(payment);

        seat.reserve();
        seatRepository.save(seat);

        return new PayResult(
                savedPayment.getPaymentId(),
                savedPayment.getStatus().name(),
                reservation.getReservationId()
        );
    }
}