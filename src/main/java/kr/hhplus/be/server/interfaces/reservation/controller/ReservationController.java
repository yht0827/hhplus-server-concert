package kr.hhplus.be.server.interfaces.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.reservation.facade.ReservationFacade;
import kr.hhplus.be.server.application.reservation.port.out.ReservationResponse;
import kr.hhplus.be.server.common.annnotation.AuthorizationToken;
import kr.hhplus.be.server.interfaces.reservation.port.in.ReserveRequest;
import kr.hhplus.be.server.interfaces.reservation.port.out.ReserveResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController implements ReservationAPI {

	private final ReservationFacade reservationFacade;

	@PostMapping
	@AuthorizationToken
	public ResponseEntity<ReserveResponse> reserve(@RequestBody final ReserveRequest reserveRequest) {
		ReservationResponse reservationResponse = reservationFacade.reserve(reserveRequest.toDto());
		ReserveResponse response = ReserveResponse.toDto(reservationResponse);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
