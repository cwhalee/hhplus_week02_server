package kr.hhplus.be.server.domain.concert.repository;

import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.domain.concert.Schedule;

public interface ScheduleRepository {
    List<Schedule> findByConcertId(Long concertId);
    Optional<Schedule> findById(Long scheduleId);
}