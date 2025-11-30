package kr.hhplus.be.server.domain.concert;

import java.time.LocalDateTime;

public class Seat {
    private final Long seatId;
    private final Long scheduleId;
    private final String section;
    private final int row;
    private final int number;
    private final int price;
    private SeatStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Seat(Long seatId, Long scheduleId, String section, int row, int number,
                int price, SeatStatus status,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.seatId = seatId;
        this.scheduleId = scheduleId;
        this.section = section;
        this.row = row;
        this.number = number;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void hold() {
        if (status != SeatStatus.AVAILABLE) {
            throw new IllegalStateException("Seat is not available.");
        }
        this.status = SeatStatus.TEMP_HOLD;
    }

    public void reserve() {
        if (status != SeatStatus.TEMP_HOLD) {
            throw new IllegalStateException("Seat is not in HOLD state.");
        }
        this.status = SeatStatus.RESERVED;
    }

    // 만료 시 다시 AVAILABLE로 돌리는 용도
    public void release() {
        if (status == SeatStatus.TEMP_HOLD) {
            this.status = SeatStatus.AVAILABLE;
        }
    }

    // Service에서 사용하는 getters
    public Long getSeatId() { return seatId; }
    public Long getScheduleId() { return scheduleId; }
    public String getSection() { return section; }
    public int getRow() { return row; }
    public int getNumber() { return number; }
    public int getPrice() { return price; }
    public SeatStatus getStatus() { return status; }
}
