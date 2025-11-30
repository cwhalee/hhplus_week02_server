package kr.hhplus.be.server.domain.user;

import java.time.LocalDateTime;

public class User {
    private final Long userId;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;

    public User(Long userId, String name, String email, LocalDateTime createdAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getUserId() { return userId; }
}
