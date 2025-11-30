package kr.hhplus.be.server.application.queue.dto;

public record QueueTokenResult(
        String token,
        int position,
        String status
) {

}