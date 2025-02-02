package kr.hhplus.be.server.application.reservation.facade;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.reservation.port.in.ReserveCommand;
import kr.hhplus.be.server.application.reservation.port.out.ReserveInfo;
import kr.hhplus.be.server.support.annnotation.DistributeLock;
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
	public ReserveInfo reserve(final ReserveCommand reserveCommand) {

		// 좌석 상태 업데이트 변경
		concertService.getAvailableSeat(reserveCommand);

		// 예약 생성
		Reservation reservation = reservationService.reserve(reserveCommand);

		// 예약 - 좌석 테이블 생성
		reservationService.createReservationSeat(reservation.getReservationId(), reserveCommand.concertSeatId());

		// 가격 조회
		Integer seatPrice = concertService.getSeatPrice(reserveCommand);

		// 결제 생성
		Payment payment = paymentService.createPayment(reservation, seatPrice);

		return ReserveInfo.toDto(reservation, payment);
	}

	@Transactional(readOnly = true)
	public Page<ConcertResponse.ConcertDateResponse> getAvailableDates(final Pageable pageable) {
		return concertService.getAvailableDates(pageable);
	}

	@Transactional(readOnly = true)
	public Page<ConcertResponse.ConcertSeatResponse> getAvailableSeatList(final String date, final Pageable pageable) {

		// 사용 가능한 좌석 리스트 조회
		Page<ConcertResponse.ConcertSeatResponse> availableSeatList = concertService.getAvailableSeatList(date,
			pageable);

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
