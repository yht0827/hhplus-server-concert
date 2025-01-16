package kr.hhplus.be.server.interfaces.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.ReservationFacade;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveRequest;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationFacade reservationFacade;

	@PostMapping
	public ResponseEntity<ReserveResponse> reserve(@RequestBody final ReserveRequest reserveRequest) {
		ReserveResponse response = ReserveResponse.toDto(reservationFacade.reserve(reserveRequest));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
