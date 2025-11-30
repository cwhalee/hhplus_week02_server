package kr.hhplus.be.server.domain.queue.repository;

import java.util.Optional;
import java.util.List;
import kr.hhplus.be.server.domain.queue.QueueToken;
import kr.hhplus.be.server.domain.queue.QueueStatus;

public interface QueueTokenRepository {

    QueueToken save(QueueToken token);

    Optional<QueueToken> findByToken(String token);

    List<QueueToken> findWaitingTokens();      // 대기중 전체 조회

    List<QueueToken> findActiveTokens();       // 활성 사용자 조회
}