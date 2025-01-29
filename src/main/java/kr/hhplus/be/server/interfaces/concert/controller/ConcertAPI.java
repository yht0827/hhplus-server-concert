package kr.hhplus.be.server.interfaces.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.interfaces.concert.port.in.ConcertDateRequest;
import kr.hhplus.be.server.interfaces.concert.port.out.AvailableDateResponseList;
import kr.hhplus.be.server.interfaces.concert.port.out.AvailableSeatResponseList;

@Tag(name = "Concert", description = "콘서트 API")
public interface ConcertAPI {

	@Operation(summary = "예약 가능 날짜 조회", description = "예약 가능한 콘서트 날짜를 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "예약 가능 날짜 조회 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = AvailableDateResponseList.class)))})
	ResponseEntity<AvailableDateResponseList> getAvailableDateList();

	@Operation(summary = "예약 가능 좌석 조회", description = "예약 가능한 콘서트 좌석을 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "예약 가능 좌석 조회 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = AvailableSeatResponseList.class)))})
	ResponseEntity<AvailableSeatResponseList> getAvailableSeatList(
		@ModelAttribute ConcertDateRequest concertDateRequest);

}
