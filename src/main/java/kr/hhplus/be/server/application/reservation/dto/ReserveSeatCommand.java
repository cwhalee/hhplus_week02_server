package kr.hhplus.be.server.application.reservation.dto;

public record ReserveSeatCommand(
        Long userId,
        Long scheduleId,
        Long seatId
) {}
