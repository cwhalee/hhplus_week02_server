package kr.hhplus.be.server.application.point;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.point.dto.UserPointInfo;
import kr.hhplus.be.server.domain.point.repository.UserPointRepository;

@Service
public class GetUserPointService implements GetUserPointUseCase {

    private final UserPointRepository userPointRepository;

    public GetUserPointService(UserPointRepository userPointRepository) {
        this.userPointRepository = userPointRepository;
    }

    @Override
    public UserPointInfo getPoint(Long userId) {
        return userPointRepository.findByUserId(userId)
                .map(p -> new UserPointInfo(
                        p.getUserId(),   // ✔ domain getter
                        p.getBalance()   // ✔ domain getter
                ))
                .orElseGet(() -> new UserPointInfo(userId, 0));
    }
}