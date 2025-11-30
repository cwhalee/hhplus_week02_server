package kr.hhplus.be.server.application.concert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.concert.dto.ScheduleInfo;
import kr.hhplus.be.server.domain.concert.Schedule;
import kr.hhplus.be.server.domain.concert.repository.ScheduleRepository;

@Service
public class GetSchedulesService implements GetSchedulesUseCase {

    private final ScheduleRepository scheduleRepository;

    public GetSchedulesService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleInfo> getSchedules(Long concertId) {
        return scheduleRepository.findByConcertId(concertId)
                .stream()
                .map(s -> new ScheduleInfo(
                        s.getScheduleId(),
                        s.getDate(),
                        s.getShowNumber(),
                        s.getStartTime(),
                        s.getEndTime()
                ))
                .collect(Collectors.toList());
    }
}