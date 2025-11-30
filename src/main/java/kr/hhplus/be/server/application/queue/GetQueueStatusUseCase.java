package kr.hhplus.be.server.application.queue;

import kr.hhplus.be.server.application.queue.dto.QueueStatusInfo;

public interface GetQueueStatusUseCase {
    QueueStatusInfo getStatus(String token);
}