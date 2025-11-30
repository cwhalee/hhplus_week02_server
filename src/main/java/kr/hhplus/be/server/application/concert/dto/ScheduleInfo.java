package kr.hhplus.be.server.application.concert.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleInfo(
        Long scheduleId,
        LocalDate date,
        int showNumber,
        LocalTime startTime,
        LocalTime endTime
) {

}