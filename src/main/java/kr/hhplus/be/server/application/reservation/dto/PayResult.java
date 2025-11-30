package kr.hhplus.be.server.application.reservation.dto;

public record PayResult(
        Long paymentId,
        String status,
        Long reservationId
) {

}