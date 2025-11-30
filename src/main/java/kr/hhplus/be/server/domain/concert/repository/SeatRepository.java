package kr.hhplus.be.server.domain.concert.repository;

import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.domain.concert.Seat;

public interface SeatRepository {
    List<Seat> findByScheduleId(Long scheduleId);
    Optional<Seat> findById(Long seatId);
    Seat save(Seat seat);             // 좌석 상태 변경 반영
}