package kr.hhplus.be.server.domain.concert.repository;

import java.util.Optional;
import kr.hhplus.be.server.domain.concert.Venue;

public interface VenueRepository {
    Optional<Venue> findById(Long venueId);
}