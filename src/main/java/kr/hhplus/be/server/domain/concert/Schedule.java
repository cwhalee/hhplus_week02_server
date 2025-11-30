package kr.hhplus.be.server.domain.concert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Schedule {
    private final Long scheduleId;
    private final Long concertId;
    private final LocalDate date;
    private final int showNumber;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final int totalSeats;
    private final ScheduleStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Schedule(Long scheduleId, Long concertId, LocalDate date, int showNumber,
                    LocalTime startTime, LocalTime endTime, int totalSeats,
                    ScheduleStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.concertId = concertId;
        this.date = date;
        this.showNumber = showNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSeats = totalSeats;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getScheduleId() { return scheduleId; }
    public Long getConcertId() { return concertId; }
    public LocalDate getDate() { return date; }
    public int getShowNumber() { return showNumber; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public int getTotalSeats() { return totalSeats; }
    public ScheduleStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}