package kr.hhplus.be.server.domain.point.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import kr.hhplus.be.server.domain.point.repository.PointRepository;
import kr.hhplus.be.server.interfaces.point.dto.ChargeRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService {

	private final PointRepository pointRepository;

	@Transactional
	public Point savePoint(final ChargeRequest chargeRequest) {
		Point point = chargeRequest.toEntity();

		return pointRepository.savePoint(point);
	}

	@Transactional
	public PointHistory chargePointHistory(final Point point) {
		PointHistory pointHistory = PointHistory.builder()
			.pointId(point.getPointId())
			.amount(point.getBalance())
			.type(PointHistory.PointTypeEnum.CHARGE)
			.comment(String.format("%d원 충전", point.getBalance()))
			.build();

		return pointRepository.savePointHistory(pointHistory);
	}

	@Transactional
	public PointHistory usePointHistory(final Point point) {
		PointHistory pointHistory = PointHistory.builder()
			.pointId(point.getPointId())
			.amount(point.getBalance())
			.type(PointHistory.PointTypeEnum.USE)
			.comment(String.format("%d원 사용", point.getBalance()))
			.build();

		return pointRepository.savePointHistory(pointHistory);
	}

	@Transactional(readOnly = true)
	public Point getPoint(final Long pointId) {
		return pointRepository.findPointById(pointId)
			.orElseThrow(() -> new CustomException(ErrorCode.POINT_NOT_FOUND));
	}
}
