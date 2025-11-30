package kr.hhplus.be.server.application.concert;

import java.util.List;
import kr.hhplus.be.server.application.concert.dto.SeatInfo;

public interface GetSeatsUseCase {
    List<SeatInfo> getSeats(Long concertId, Long scheduleId);
}