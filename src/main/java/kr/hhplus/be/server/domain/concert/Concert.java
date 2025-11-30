package kr.hhplus.be.server.domain.concert;

import java.time.LocalDateTime;

public class Concert {
    private final Long concertId;
    private final Long venueId;
    private final String title;
    private final String description;
    private final String genre;
    private final ConcertStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Concert(Long concertId, Long venueId, String title, String description,
                   String genre, ConcertStatus status,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.concertId = concertId;
        this.venueId = venueId;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getConcertId() { return concertId; }
    public Long getVenueId() { return venueId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getGenre() { return genre; }
    public ConcertStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
