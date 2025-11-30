package kr.hhplus.be.server.application.point;

import kr.hhplus.be.server.application.point.dto.ChargePointCommand;
import kr.hhplus.be.server.application.point.dto.UserPointInfo;

public interface ChargeUserPointUseCase {
    UserPointInfo charge(ChargePointCommand command);
}