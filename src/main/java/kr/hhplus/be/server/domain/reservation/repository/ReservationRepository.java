package kr.hhplus.be.server.domain.reservation.repository;

import java.util.Optional;
import java.util.List;
import kr.hhplus.be.server.domain.reservation.Reservation;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long reservationId);
    List<Reservation> findExpiredReservations();  // 만료 스케줄러용
}