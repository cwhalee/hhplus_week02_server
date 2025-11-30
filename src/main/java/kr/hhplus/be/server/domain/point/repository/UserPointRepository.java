package kr.hhplus.be.server.domain.point.repository;

import java.util.Optional;
import kr.hhplus.be.server.domain.point.UserPoint;

public interface UserPointRepository {
    Optional<UserPoint> findByUserId(Long userId);
    UserPoint save(UserPoint userPoint);
}