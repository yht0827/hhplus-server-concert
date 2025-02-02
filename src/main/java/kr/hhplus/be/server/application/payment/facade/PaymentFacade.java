package kr.hhplus.be.server.application.payment.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.payment.port.in.ChargePointRequest;
import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.application.payment.port.out.PaymentResponse;
import kr.hhplus.be.server.application.payment.port.out.PointChargeResponse;
import kr.hhplus.be.server.support.annnotation.DistributeLock;
import kr.hhplus.be.server.domain.concert.service.ConcertService;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.service.PaymentService;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import kr.hhplus.be.server.domain.point.service.PointService;
import kr.hhplus.be.server.domain.reservation.service.ReservationService;
import kr.hhplus.be.server.domain.token.service.TokenService;
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
	public PointChargeResponse chargePoint(final ChargePointRequest chargePointRequest) {
		Point point = pointService.chargePoint(chargePointRequest);

		PointHistory pointHistory = pointService.chargePointHistory(point, chargePointRequest);

		return PointChargeResponse.toDto(pointHistory);
	}

	@Transactional(readOnly = true)
	public Point getPoint(final Long pointId) {
		return pointService.getPoint(pointId);
	}

	@DistributeLock(key = "'payment:' + #paymentRequest.reservationId()")
	@Transactional
	public PaymentResponse paymentConcert(final PaymentRequest paymentRequest) {

		// 좌석 상태 업데이트
		reservationService.updateStatus(paymentRequest.reservationId());

		// 결제 정보 업데이트
		Payment payment = paymentService.paymentConcert(paymentRequest);

		// 콘서트 좌석 개수 감소
		concertService.decreaseSeatCount(paymentRequest);

		// 포인트 차감
		Point point = pointService.usePoint(paymentRequest);

		// 포인트 사용 내역 저장
		pointService.usePointHistory(point);

		// 토큰 만료 (ACTIVE -> EXPIRED)
		tokenService.updateExpireToken(paymentRequest.userId());

		return PaymentResponse.toDto(payment);
	}
}
