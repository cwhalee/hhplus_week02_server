package kr.hhplus.be.server.application.point;

import kr.hhplus.be.server.application.point.dto.UserPointInfo;

public interface GetUserPointUseCase {
    UserPointInfo getPoint(Long userId);
}