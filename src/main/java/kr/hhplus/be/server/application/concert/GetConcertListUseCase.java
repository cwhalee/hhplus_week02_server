package kr.hhplus.be.server.application.concert;

import java.util.List;
import kr.hhplus.be.server.application.concert.dto.ConcertInfo;

public interface GetConcertListUseCase {
    List<ConcertInfo> getConcerts();
}