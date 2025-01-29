package kr.hhplus.be.server.interfaces.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.reservation.facade.ReservationFacade;
import kr.hhplus.be.server.interfaces.concert.port.in.ConcertDateRequest;
import kr.hhplus.be.server.interfaces.concert.port.out.AvailableDateResponseList;
import kr.hhplus.be.server.interfaces.concert.port.out.AvailableSeatResponseList;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController implements ConcertAPI {

	private final ReservationFacade reservationFacade;

	@GetMapping("/availableDate")
	public ResponseEntity<AvailableDateResponseList> getAvailableDateList() {
		AvailableDateResponseList response = AvailableDateResponseList.toDto(reservationFacade.getAvailableDates());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/availableSeat")
	public ResponseEntity<AvailableSeatResponseList> getAvailableSeatList(
		@ModelAttribute ConcertDateRequest concertDateRequest) {

		AvailableSeatResponseList response = AvailableSeatResponseList
			.toDto(reservationFacade.getAvailableSeatList(concertDateRequest.date()));

		return ResponseEntity.ok(response);
	}
}
