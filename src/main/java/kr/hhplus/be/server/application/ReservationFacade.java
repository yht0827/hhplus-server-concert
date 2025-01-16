package kr.hhplus.be.server.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.concert.service.ConcertService;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.reservation.service.ReservationService;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

	private final ReservationService reservationService;
	private final ConcertService concertService;

	@Transactional
	public Reservation reserve(final ReserveRequest reserveRequest) {

		// 이미 완료된 좌석이 존재하는지 조회
		concertService.findReservedConcertSeat(reserveRequest);

		// 좌석 생성 또는 업데이트
		ConcertSeat concertSeat = concertService.upsertConcertSeat(reserveRequest);

		// 예약 생성
		return reservationService.reserve(reserveRequest, concertSeat.getConcertSeatId());
	}
}
