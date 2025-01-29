package kr.hhplus.be.server.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import kr.hhplus.be.server.domain.point.service.PointService;
import kr.hhplus.be.server.interfaces.point.dto.ChargeRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PointFacade {
	private final PointService pointService;


	public PointHistory chargePoint(final ChargeRequest chargeRequest) {
		Point point = pointService.chargePoint(chargeRequest);

		return pointService.chargePointHistory(point, chargeRequest);
	}

	@Transactional
	public Point searchPoint(final Long pointId) {
		return pointService.getPoint(pointId);
	}

}
