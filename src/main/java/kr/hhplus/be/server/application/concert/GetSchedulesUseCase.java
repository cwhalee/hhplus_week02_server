package kr.hhplus.be.server.application.concert;

import java.util.List;
import kr.hhplus.be.server.application.concert.dto.ScheduleInfo;

public interface GetSchedulesUseCase {
    List<ScheduleInfo> getSchedules(Long concertId);
}