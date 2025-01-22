package kr.hhplus.be.server.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.concert.entity.Concert;
import kr.hhplus.be.server.domain.concert.service.ConcertService;
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
	private final ConcertService concertService;
	private final ReservationService reservationService;
	private final PointService pointService;

	@Transactional
	public Payment paymentConcert(final PaymentConcertRequest paymentConcertRequest) {

		// 이미 결제가 완료 되었는지 체크

		// 콘서트 가격 조회
		Concert concert = concertService.getConcert(paymentConcertRequest.concertId());

		// 포인트 차감
		Point point = pointService.usePoint(paymentConcertRequest);

		// 결제 저장
		Payment payment = paymentService.paymentConcert(paymentConcertRequest, concert.getConcertPrice());

		// 포인트 내역 저장
		pointService.usePointHistory(point);

		// 좌석 업데이트
		reservationService.updateStatus(payment.getReservationId());

		// 토큰 만료
		tokenService.updateExpireToken(paymentConcertRequest.userId());

		return payment;
	}
}
