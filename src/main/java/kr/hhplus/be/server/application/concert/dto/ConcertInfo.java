package kr.hhplus.be.server.application.concert.dto;

public record ConcertInfo(
        Long concertId,
        String title,
        String genre,
        Long venueId,
        String venueName
) {

}