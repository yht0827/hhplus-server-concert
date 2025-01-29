package kr.hhplus.be.server.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.common.annnotation.DistributeLock;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.service.PaymentService;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.service.PointService;
import kr.hhplus.be.server.domain.reservation.service.ReservationService;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

	private final PaymentService paymentService;
	private final TokenService tokenService;
	private final ReservationService reservationService;
	private final PointService pointService;

	@DistributeLock(key = "'payment:' + #paymentConcertRequest.reservationId()")
	public Payment paymentConcert(final PaymentConcertRequest paymentConcertRequest) {

		// 좌석 상태 (RESERVED 인지 확인)
		reservationService.checkReservedStatus(paymentConcertRequest.reservationId());

		// 포인트 차감
		Point point = pointService.usePoint(paymentConcertRequest);

		// 결제 저장
		Payment payment = paymentService.paymentConcert(paymentConcertRequest);

		// 포인트 사용 내역 저장
		pointService.usePointHistory(point);

		// 좌석 상태 업데이트 (WAIT -> RESERVED)
		reservationService.updateStatus(payment.getReservationId());

		// 토큰 만료 (ACTIVE -> EXPIRED)
		tokenService.updateExpireToken(paymentConcertRequest.userId());

		return payment;
	}
}
