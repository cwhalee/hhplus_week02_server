package kr.hhplus.be.server.application.queue;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.queue.dto.IssueQueueTokenCommand;
import kr.hhplus.be.server.application.queue.dto.QueueTokenResult;
import kr.hhplus.be.server.domain.queue.QueueStatus;
import kr.hhplus.be.server.domain.queue.QueueToken;
import kr.hhplus.be.server.domain.queue.repository.QueueTokenRepository;

@Service
public class IssueQueueTokenService implements IssueQueueTokenUseCase {

    private final QueueTokenRepository queueTokenRepository;

    public IssueQueueTokenService(QueueTokenRepository queueTokenRepository) {
        this.queueTokenRepository = queueTokenRepository;
    }

    @Override
    @Transactional
    public QueueTokenResult issue(IssueQueueTokenCommand command) {

        String token = UUID.randomUUID().toString();
        int position = queueTokenRepository.findWaitingTokens().size() + 1;

        QueueToken queueToken = new QueueToken(
                token,
                command.userId(),
                QueueStatus.WAITING,
                position,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30) // 예시
        );

        queueTokenRepository.save(queueToken);

        return new QueueTokenResult(token, position, queueToken.getStatus().name());
    }
}
