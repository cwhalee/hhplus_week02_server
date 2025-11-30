package kr.hhplus.be.server.domain.point;

import java.time.LocalDateTime;

public class UserPoint {

    private final Long userId;
    private long balance;
    private LocalDateTime updatedAt;

    public UserPoint(Long userId, long balance, LocalDateTime updatedAt) {
        this.userId = userId;
        this.balance = balance;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public long getBalance() {
        return balance;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void charge(long amount) {
        this.balance += amount;
        this.updatedAt = LocalDateTime.now();
    }

    public void use(long amount) {
        if (this.balance < amount) {
            throw new IllegalStateException("Not enough point");
        }
        this.balance -= amount;
        this.updatedAt = LocalDateTime.now();
    }
}

