package kr.hhplus.be.server.interfaces.reservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.interfaces.reservation.port.in.ReserveRequest;
import kr.hhplus.be.server.interfaces.reservation.port.out.ReserveResponse;

@Tag(name = "Reservation", description = "예약 API")
public interface ReservationAPI {

	@Operation(summary = "콘서트 좌석 예약", description = "콘서트 좌석 예약을 진행합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "콘서트 좌석 예약 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReserveResponse.class)))})
	ResponseEntity<ReserveResponse> reserve(@RequestBody final ReserveRequest reserveRequest);
}
