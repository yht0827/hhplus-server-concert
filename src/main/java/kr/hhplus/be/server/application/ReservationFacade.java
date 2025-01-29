package kr.hhplus.be.server.application;

import org.springframework.stereotype.Component;

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

	public Reservation reserve(final ReserveRequest reserveRequest) {

		// 이미 예약이 완료된 좌석인지 조회
		concertService.findReservedConcertSeat(reserveRequest);

		// 예약 생성
		Reservation reservation = reservationService.reserve(reserveRequest);

		// 좌석 생성 또는 업데이트
		concertService.upsertConcertSeat(reserveRequest);

		return reservation;
	}
}
