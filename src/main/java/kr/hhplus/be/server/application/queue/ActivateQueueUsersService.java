package kr.hhplus.be.server.application.queue;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.queue.QueueStatus;
import kr.hhplus.be.server.domain.queue.repository.QueueTokenRepository;

@Service
public class ActivateQueueUsersService implements ActivateQueueUsersUseCase {

    private final QueueTokenRepository queueTokenRepository;

    public ActivateQueueUsersService(QueueTokenRepository queueTokenRepository) {
        this.queueTokenRepository = queueTokenRepository;
    }

    @Transactional
    @Override
    public void activateNextBatch() {

        var waitingList = queueTokenRepository.findWaitingTokens();

        int activateCount = 10;
        waitingList.stream()
                .limit(activateCount)
                .forEach(token -> {
                    token.activate();
                    queueTokenRepository.save(token);
                });
    }
}