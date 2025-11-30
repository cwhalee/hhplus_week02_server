package kr.hhplus.be.server.application.reservation.dto;

import java.time.LocalDateTime;

public record ReservationResult(
        Long reservationId,
        Long seatId,
        String status,
        LocalDateTime expiredAt
) {

}