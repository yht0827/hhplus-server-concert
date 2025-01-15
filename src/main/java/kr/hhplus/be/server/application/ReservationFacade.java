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

	public void reserve(final ReserveRequest reserveRequest) {

		Reservation reservation = reservationService.reserve(reserveRequest);



	}
}
