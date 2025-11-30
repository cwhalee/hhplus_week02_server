package kr.hhplus.be.server.application.queue;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.queue.QueueStatus;
import kr.hhplus.be.server.domain.queue.repository.QueueTokenRepository;

@Service
public class ValidateQueueTokenService implements ValidateQueueTokenUseCase {

    private final QueueTokenRepository queueTokenRepository;

    public ValidateQueueTokenService(QueueTokenRepository queueTokenRepository) {
        this.queueTokenRepository = queueTokenRepository;
    }

    @Override
    public void validate(String token) {
        var queueToken = queueTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (queueToken.getStatus() != QueueStatus.ACTIVE) {
            throw new IllegalStateException("User is not activated");
        }
    }
}