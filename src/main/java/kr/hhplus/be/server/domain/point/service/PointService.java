package kr.hhplus.be.server.domain.point.service;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.payment.port.in.ChargePointRequest;
import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import kr.hhplus.be.server.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {

	private final PointRepository pointRepository;

	public Point chargePoint(final ChargePointRequest chargePointRequest) {
		Point point = pointRepository.findPointByUserId(chargePointRequest.userId())
			.orElseThrow(() -> new CustomException(ErrorCode.POINT_NOT_FOUND));

		point.chargePoint(chargePointRequest.point());

		log.info("charge point: {}", point.getBalance());
		return point;
	}

	public Point usePoint(final PaymentRequest paymentRequest) {
		Point point = pointRepository.findPointByUserId(paymentRequest.userId())
			.orElseThrow(() -> new CustomException(ErrorCode.POINT_NOT_FOUND));

		if (point.getBalance() < paymentRequest.price()) {
			throw new CustomException(ErrorCode.NOT_ENOUGH_BALANCE);
		}

		point.usePoint(paymentRequest.price());

		log.info("use point: {}", point.getBalance());
		return point;
	}

	public PointHistory chargePointHistory(final Point point, final ChargePointRequest chargePointRequest) {
		PointHistory pointHistory = PointHistory.builder()
			.pointId(point.getPointId())
			.amount(chargePointRequest.point())
			.type(PointHistory.PointTypeEnum.CHARGE)
			.comment(String.format("%d원 충전", point.getBalance()))
			.build();

		return pointRepository.savePointHistory(pointHistory);
	}

	public PointHistory usePointHistory(final Point point) {
		PointHistory pointHistory = PointHistory.builder()
			.pointId(point.getPointId())
			.amount(point.getBalance())
			.type(PointHistory.PointTypeEnum.USE)
			.comment(String.format("%d원 사용", point.getBalance()))
			.build();

		return pointRepository.savePointHistory(pointHistory);
	}

	public Point getPoint(final Long pointId) {
		return pointRepository.findPointById(pointId)
			.orElseThrow(() -> new CustomException(ErrorCode.POINT_NOT_FOUND));
	}
}
