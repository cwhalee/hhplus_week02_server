package kr.hhplus.be.server.application.reservation;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.concert.repository.SeatRepository;
import kr.hhplus.be.server.domain.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpireReservationService implements ExpireReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    public ExpireReservationService(ReservationRepository reservationRepository,
                                    SeatRepository seatRepository) {
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional
    public void expireReservations() {
        reservationRepository.findExpiredReservations().forEach(reservation -> {

            reservation.expire();
            reservationRepository.save(reservation);

            Seat seat = seatRepository.findById(reservation.getSeatId()).get();
            seat.release(); // release 메서드는 도메인에 추가 필요
            seatRepository.save(seat);
        });
    }
}