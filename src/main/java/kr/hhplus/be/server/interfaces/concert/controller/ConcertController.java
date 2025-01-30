package kr.hhplus.be.server.interfaces.concert.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.reservation.facade.ReservationFacade;
import kr.hhplus.be.server.common.response.PageResponse;
import kr.hhplus.be.server.interfaces.concert.port.in.ConcertDateRequest;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController implements ConcertAPI {

	private final ReservationFacade reservationFacade;

	@GetMapping("/availableDate")
	public ResponseEntity<PageResponse<ConcertResponse.ConcertDateResponse>> getAvailableDateList(
		final Pageable pageable) {
		PageResponse<ConcertResponse.ConcertDateResponse> response = PageResponse.toDto(
			reservationFacade.getAvailableDates(pageable));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/availableSeat")
	public ResponseEntity<PageResponse<ConcertResponse.ConcertSeatResponse>> getAvailableSeatList(
		@ModelAttribute ConcertDateRequest concertDateRequest, final Pageable pageable) {

		PageResponse<ConcertResponse.ConcertSeatResponse> response = PageResponse
			.toDto(reservationFacade.getAvailableSeatList(concertDateRequest.date(), pageable));

		return ResponseEntity.ok(response);
	}
}
