package kr.hhplus.be.server.application.concert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.concert.dto.SeatInfo;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.concert.repository.SeatRepository;

@Service
public class GetSeatsService implements GetSeatsUseCase {

    private final SeatRepository seatRepository;

    public GetSeatsService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<SeatInfo> getSeats(Long concertId, Long scheduleId) {

        // concertId는 검증만 하고 실제 Seat는 schedule 기반으로 조회
        return seatRepository.findByScheduleId(scheduleId)
                .stream()
                .map(s -> new SeatInfo(
                        s.getSeatId(),
                        s.getSection(),
                        s.getRow(),
                        s.getNumber(),
                        s.getPrice(),
                        s.getStatus().name()
                ))
                .collect(Collectors.toList());
    }
}