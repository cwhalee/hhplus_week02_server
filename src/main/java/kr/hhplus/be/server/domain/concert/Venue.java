package kr.hhplus.be.server.domain.concert;

import java.time.LocalDateTime;

public class Venue {
    private final Long venueId;
    private final String name;
    private final String address;
    private final int capacity;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Venue(Long venueId, String name, String address, int capacity,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.venueId = venueId;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getVenueId() { return venueId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public int getCapacity() { return capacity; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
