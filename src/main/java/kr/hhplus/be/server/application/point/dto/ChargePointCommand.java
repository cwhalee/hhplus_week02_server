package kr.hhplus.be.server.application.point.dto;

public record ChargePointCommand(
        Long userId,
        long amount
) {

}
