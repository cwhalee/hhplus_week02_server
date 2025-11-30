package kr.hhplus.be.server.domain.reservation;

import java.time.LocalDateTime;

public class Reservation {
    private final Long reservationId;
    private final Long seatId;
    private ReservationStatus status;
    private final LocalDateTime reservedAt;
    private LocalDateTime expiredAt;
    private LocalDateTime paidAt;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Reservation(Long reservationId, Long seatId, ReservationStatus status,
                       LocalDateTime reservedAt, LocalDateTime expiredAt,
                       LocalDateTime paidAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.reservationId = reservationId;
        this.seatId = seatId;
        this.status = status;
        this.reservedAt = reservedAt;
        this.expiredAt = expiredAt;
        this.paidAt = paidAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void confirm() { this.status = ReservationStatus.CONFIRMED; }
    public void cancel() { this.status = ReservationStatus.CANCELED; }
    public void expire() { this.status = ReservationStatus.CANCELED; }

    // ===== Service에서 사용하는 getter들 =====
    public Long getReservationId() { return reservationId; }
    public Long getSeatId() { return seatId; }
    public ReservationStatus getStatus() { return status; }
    public LocalDateTime getExpiredAt() { return expiredAt; }
}
