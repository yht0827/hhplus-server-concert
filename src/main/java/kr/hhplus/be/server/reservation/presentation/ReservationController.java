package kr.hhplus.be.server.reservation.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.reservation.presentation.dto.ReserveRequest;
import kr.hhplus.be.server.reservation.presentation.dto.ReserveResponse;

@RestController
@RequestMapping("/reserve")
public class ReservationController {

	@PostMapping
	public ResponseEntity<ReserveResponse> reserve(@RequestBody ReserveRequest request) {

		ReserveResponse response = ReserveResponse.builder()
			.message("좌석 예약 성공(임시 배정)")
			.reserveId(1L)
			.build();

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
