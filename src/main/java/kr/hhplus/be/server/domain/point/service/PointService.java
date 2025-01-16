package kr.hhplus.be.server.domain.point.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import kr.hhplus.be.server.domain.point.repository.PointRepository;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertRequest;
import kr.hhplus.be.server.interfaces.point.dto.ChargeRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService {

	private final PointRepository pointRepository;

	@Transactional
	public Point chargePoint(final ChargeRequest chargeRequest) {
		Point point = pointRepository.findPointByUserId(chargeRequest.userId())
			.orElseThrow(() -> new CustomException(ErrorCode.POINT_NOT_FOUND));

		point.chargePoint(chargeRequest.point());

		return point;
	}

	@Transactional
	public Point usePoint(final PaymentConcertRequest paymentConcertRequest) {
		Point point = pointRepository.findPointByUserId(paymentConcertRequest.userId())
			.orElseThrow(() -> new CustomException(ErrorCode.POINT_NOT_FOUND));

		if (point.getBalance() < paymentConcertRequest.price()) {
			throw new CustomException(ErrorCode.NOT_ENOUGH_BALANCE);
		}

		point.usePoint(paymentConcertRequest.price());

		return point;
	}

	@Transactional
	public PointHistory chargePointHistory(final Point point, final ChargeRequest chargeRequest) {
		PointHistory pointHistory = PointHistory.builder()
			.pointId(point.getPointId())
			.amount(chargeRequest.point())
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
