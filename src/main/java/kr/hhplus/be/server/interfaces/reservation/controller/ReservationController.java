package kr.hhplus.be.server.interfaces.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.interfaces.reservation.dto.ReserveRequest;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveResponse;

@RestController
@RequestMapping("/reserve")
public class ReservationController {

	@PostMapping
	public ResponseEntity<ReserveResponse> reserve(@RequestBody ReserveRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

}
