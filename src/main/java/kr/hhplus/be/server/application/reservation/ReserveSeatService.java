package kr.hhplus.be.server.application.reservation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.reservation.dto.ReserveSeatCommand;
import kr.hhplus.be.server.application.reservation.dto.ReservationResult;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.concert.repository.SeatRepository;
import kr.hhplus.be.server.domain.reservation.Reservation;
import kr.hhplus.be.server.domain.reservation.ReservationStatus;
import kr.hhplus.be.server.domain.reservation.repository.ReservationRepository;

@Service
public class ReserveSeatService implements ReserveSeatUseCase {

    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    public ReserveSeatService(
            SeatRepository seatRepository,
            ReservationRepository reservationRepository
    ) {
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public ReservationResult reserve(ReserveSeatCommand command) {

        Seat seat = seatRepository.findById(command.seatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        // 비즈니스 규칙 실행
        seat.hold();

        seatRepository.save(seat);

        LocalDateTime expireAt = LocalDateTime.now().plusMinutes(5);

        Reservation reservation = new Reservation(
                null,
                command.seatId(),
                ReservationStatus.HOLD,
                LocalDateTime.now(),
                expireAt,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Reservation saved = reservationRepository.save(reservation);

        return new ReservationResult(
                saved.getReservationId(),
                saved.getSeatId(),
                saved.getStatus().name(),
                saved.getExpiredAt()
        );
    }
}