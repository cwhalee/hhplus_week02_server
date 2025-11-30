package kr.hhplus.be.server.domain.point;

import java.time.LocalDateTime;

public class PointHistory {
    private final Long historyId;
    private final Long userId;
    private final long amount;
    private final PointHistoryType type;
    private final LocalDateTime createdAt;

    public PointHistory(Long historyId, Long userId, long amount,
                        PointHistoryType type, LocalDateTime createdAt) {
        this.historyId = historyId;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
    }
}
