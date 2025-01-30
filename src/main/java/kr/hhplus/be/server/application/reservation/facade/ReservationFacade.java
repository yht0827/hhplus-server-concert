package kr.hhplus.be.server.application.reservation.facade;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.reservation.port.in.ReserveSeatRequest;
import kr.hhplus.be.server.application.reservation.port.out.ReservationResponse;
import kr.hhplus.be.server.common.annnotation.DistributeLock;
import kr.hhplus.be.server.domain.concert.service.ConcertService;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.service.PaymentService;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.reservation.service.ReservationService;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

	private final ReservationService reservationService;
	private final ConcertService concertService;
	private final PaymentService paymentService;

	@DistributeLock(key = "'reserve:' + #reserveRequest.concertSeatId()")
	@Transactional
	public ReservationResponse reserve(final ReserveSeatRequest reserveSeatRequest) {

		// 좌석 상태 업데이트 변경
		concertService.getAvailableSeat(reserveSeatRequest);

		// 예약 생성
		Reservation reservation = reservationService.reserve(reserveSeatRequest);

		// 예약 - 좌석 테이블 생성
		reservationService.createReservationSeat(reservation.getReservationId(), reserveSeatRequest.concertSeatId());

		// 가격 조회
		Integer seatPrice = concertService.getSeatPrice(reserveSeatRequest);

		// 결제 생성
		Payment payment = paymentService.createPayment(reservation, seatPrice);

		return ReservationResponse.toDto(reservation, payment);
	}

	@Transactional(readOnly = true)
	public List<ConcertResponse.ConcertDateResponse> getAvailableDates() {
		return concertService.getAvailableDates();
	}

	@Transactional(readOnly = true)
	public List<ConcertResponse.ConcertSeatResponse> getAvailableSeatList(final String date) {

		// 사용 가능한 좌석 리스트 조회
		List<ConcertResponse.ConcertSeatResponse> availableSeatList = concertService.getAvailableSeatList(date);

		//  콘서트 ID 리스트 추출
		List<Long> concertIds = concertService.extractConcertIds(availableSeatList);

		// 예약된 좌석 리스트 조회
		List<ConcertResponse.ConcertSeatInfoResponse> reservedSeatList = concertService.getReservedSeatList(concertIds);

		// 예약된 좌석 정보를 Map(ConcertId, 예약된 자리 Id 리스트) 변환
		Map<Long, List<Integer>> reversedSeatMap = concertService.createReservedSeatMap(reservedSeatList);

		// 사용 가능한 좌석 정보 업데이트
		concertService.updateAvailableSeats(availableSeatList, reversedSeatMap);

		return availableSeatList;
	}

}
