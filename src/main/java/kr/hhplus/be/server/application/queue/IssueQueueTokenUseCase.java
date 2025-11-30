package kr.hhplus.be.server.application.queue;

import kr.hhplus.be.server.application.queue.dto.IssueQueueTokenCommand;
import kr.hhplus.be.server.application.queue.dto.QueueTokenResult;

public interface IssueQueueTokenUseCase {
    QueueTokenResult issue(IssueQueueTokenCommand command);
}