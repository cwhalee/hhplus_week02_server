package kr.hhplus.be.server.application.concert;

import java.util.List;
import java.util.stream.Collectors;

import kr.hhplus.be.server.domain.concert.repository.VenueRepository;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.concert.dto.ConcertInfo;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.concert.repository.ConcertRepository;

@Service
public class GetConcertListService implements GetConcertListUseCase {

    private final ConcertRepository concertRepository;
    private final VenueRepository venueRepository;

    public GetConcertListService(ConcertRepository concertRepository,
                                 VenueRepository venueRepository) {
        this.concertRepository = concertRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public List<ConcertInfo> getConcerts() {
        return concertRepository.findAll()
                .stream()
                .map(c -> {
                    var venue = venueRepository.findById(c.getVenueId())
                            .orElseThrow(() -> new IllegalArgumentException("Venue not found"));

                    return new ConcertInfo(
                            c.getConcertId(),
                            c.getTitle(),
                            c.getGenre(),
                            c.getVenueId(),
                            venue.getName()   // 공연장 이름 가져오기
                    );
                })
                .collect(Collectors.toList());
    }
}
