package kr.hhplus.be.server.domain.concert.repository;

import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.domain.concert.Concert;

public interface ConcertRepository {
    List<Concert> findAll();
    Optional<Concert> findById(Long concertId);
}