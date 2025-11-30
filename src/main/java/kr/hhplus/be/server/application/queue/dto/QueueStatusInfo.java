package kr.hhplus.be.server.application.queue.dto;

public record QueueStatusInfo(
        String token,
        int position,
        String status
) {

}