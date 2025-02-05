package kr.hhplus.be.server.application.payment.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.payment.port.in.ChargeCommand;
import kr.hhplus.be.server.application.payment.port.in.PaymentCommand;
import kr.hhplus.be.server.application.payment.port.out.PaymentInfo;
import kr.hhplus.be.server.application.payment.port.out.ChargeInfo;
import kr.hhplus.be.server.domain.concert.service.ConcertService;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.service.PaymentService;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import kr.hhplus.be.server.domain.point.service.PointService;
import kr.hhplus.be.server.domain.reservation.service.ReservationService;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.support.annnotation.DistributeLock;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentFacade {
	private final PointService pointService;
	private final PaymentService paymentService;
	private final TokenService tokenService;
	private final ReservationService reservationService;
	private final ConcertService concertService;

	@DistributeLock(key = "'charge:' + #chargePointRequest.userId()")
	@Transactional
	public ChargeInfo chargePoint(final ChargeCommand chargeCommand) {
		Point point = pointService.chargePoint(chargeCommand);

		PointHistory pointHistory = pointService.chargePointHistory(point, chargeCommand);

		return ChargeInfo.toDto(pointHistory);
	}

	@Transactional(readOnly = true)
	public Point getPoint(final Long pointId) {
		return pointService.getPoint(pointId);
	}

	@DistributeLock(key = "'payment:' + #paymentRequest.reservationId()")
	@Transactional
	public PaymentInfo paymentConcert(final PaymentCommand paymentCommand) {

		// 좌석 상태 업데이트
		reservationService.updateStatus(paymentCommand.reservationId());

		// 결제 정보 업데이트
		Payment payment = paymentService.paymentConcert(paymentCommand);

		// 콘서트 좌석 개수 감소
		concertService.decreaseSeatCount(paymentCommand);

		// 포인트 차감
		Point point = pointService.usePoint(paymentCommand);

		// 포인트 사용 내역 저장
		pointService.usePointHistory(point);

		// 토큰 만료 (ACTIVE -> EXPIRED)
		tokenService.updateExpireToken(paymentCommand.userId());

		return PaymentInfo.toDto(payment);
	}
}
