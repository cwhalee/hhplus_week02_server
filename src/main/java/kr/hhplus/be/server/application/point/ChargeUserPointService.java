package kr.hhplus.be.server.application.point;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.point.dto.ChargePointCommand;
import kr.hhplus.be.server.application.point.dto.UserPointInfo;
import kr.hhplus.be.server.domain.point.UserPoint;
import kr.hhplus.be.server.domain.point.repository.UserPointRepository;
import kr.hhplus.be.server.domain.point.PointHistory;
import kr.hhplus.be.server.domain.point.PointHistoryType;
import kr.hhplus.be.server.domain.point.repository.PointHistoryRepository;

@Service
public class ChargeUserPointService implements ChargeUserPointUseCase {

    private final UserPointRepository userPointRepository;
    private final PointHistoryRepository historyRepository;

    public ChargeUserPointService(UserPointRepository userPointRepository,
                                  PointHistoryRepository historyRepository) {
        this.userPointRepository = userPointRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    @Transactional
    public UserPointInfo charge(ChargePointCommand command) {

        UserPoint point = userPointRepository.findByUserId(command.userId())
                .orElse(new UserPoint(command.userId(), 0, java.time.LocalDateTime.now()));

        point.charge(command.amount());
        userPointRepository.save(point);

        historyRepository.save(new PointHistory(
                null,
                command.userId(),
                command.amount(),
                PointHistoryType.CHARGE,
                java.time.LocalDateTime.now()
        ));

        return new UserPointInfo(point.getUserId(), point.getBalance());
    }
}
