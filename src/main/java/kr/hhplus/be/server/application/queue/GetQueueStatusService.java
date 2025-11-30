package kr.hhplus.be.server.application.queue;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.queue.dto.QueueStatusInfo;
import kr.hhplus.be.server.domain.queue.repository.QueueTokenRepository;

@Service
public class GetQueueStatusService implements GetQueueStatusUseCase {

    private final QueueTokenRepository queueTokenRepository;

    public GetQueueStatusService(QueueTokenRepository queueTokenRepository) {
        this.queueTokenRepository = queueTokenRepository;
    }

    @Override
    public QueueStatusInfo getStatus(String token) {
        var queueToken = queueTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        return new QueueStatusInfo(
                queueToken.getToken(),
                queueToken.getPosition(),
                queueToken.getStatus().name()
        );
    }
}