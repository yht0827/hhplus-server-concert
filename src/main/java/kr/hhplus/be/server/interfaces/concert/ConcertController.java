package kr.hhplus.be.server.interfaces.concert;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.reservation.facade.ReservationFacade;
import kr.hhplus.be.server.interfaces.concert.port.out.AvailableDateResponseList;
import kr.hhplus.be.server.interfaces.concert.port.out.AvailableSeatResponseList;
import kr.hhplus.be.server.interfaces.concert.port.in.ConcertDateRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController {

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
