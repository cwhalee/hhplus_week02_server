package kr.hhplus.be.server.application.concert.dto;

public record SeatInfo(
        Long seatId,
        String section,
        int row,
        int number,
        int price,
        String status
) {

}