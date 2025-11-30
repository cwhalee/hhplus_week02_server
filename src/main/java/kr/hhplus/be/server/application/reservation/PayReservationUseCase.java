package kr.hhplus.be.server.application.reservation;

import kr.hhplus.be.server.application.reservation.dto.PayReservationCommand;
import kr.hhplus.be.server.application.reservation.dto.PayResult;

public interface PayReservationUseCase {
    PayResult pay(PayReservationCommand command);
}