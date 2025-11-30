package kr.hhplus.be.server.domain.queue;

import java.time.LocalDateTime;

public class QueueToken {
    private final String token;
    private final Long userId;
    private QueueStatus status;
    private final int position;
    private final LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    public QueueToken(String token, Long userId, QueueStatus status,
                      int position, LocalDateTime createdAt, LocalDateTime expiredAt) {
        this.token = token;
        this.userId = userId;
        this.status = status;
        this.position = position;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }

    public void activate() { this.status = QueueStatus.ACTIVE; }
    public void expire() { this.status = QueueStatus.EXPIRED; }

    public String getToken() { return token; }
    public Long getUserId() { return userId; }
    public QueueStatus getStatus() { return status; }
    public int getPosition() { return position; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getExpiredAt() { return expiredAt; }
}