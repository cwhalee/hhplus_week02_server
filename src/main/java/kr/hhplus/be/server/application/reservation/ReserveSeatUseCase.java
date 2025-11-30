package kr.hhplus.be.server.application.reservation;

import kr.hhplus.be.server.application.reservation.dto.ReserveSeatCommand;
import kr.hhplus.be.server.application.reservation.dto.ReservationResult;

public interface ReserveSeatUseCase {
    ReservationResult reserve(ReserveSeatCommand command);
}