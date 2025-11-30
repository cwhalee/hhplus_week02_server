package kr.hhplus.be.server.application.reservation.dto;

public record PayReservationCommand(
        Long userId,
        Long reservationId
) {

}