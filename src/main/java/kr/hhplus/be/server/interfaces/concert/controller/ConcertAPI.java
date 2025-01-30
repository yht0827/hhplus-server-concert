package kr.hhplus.be.server.interfaces.concert.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.common.response.PageResponse;
import kr.hhplus.be.server.interfaces.concert.port.in.ConcertDateRequest;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;

@Tag(name = "Concert", description = "콘서트 API")
public interface ConcertAPI {

	@Operation(summary = "예약 가능 날짜 조회", description = "예약 가능한 콘서트 날짜를 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "예약 가능 날짜 조회 성공",
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class)))
		}, security = @SecurityRequirement(name = "Authorization"))
	ResponseEntity<PageResponse<ConcertResponse.ConcertDateResponse>> getAvailableDateList(final Pageable pageable);

	@Operation(summary = "예약 가능 좌석 조회", description = "예약 가능한 콘서트 좌석을 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "예약 가능 좌석 조회 성공",
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class)))
		}, security = @SecurityRequirement(name = "Authorization"))
	ResponseEntity<PageResponse<ConcertResponse.ConcertSeatResponse>> getAvailableSeatList(
		@ModelAttribute ConcertDateRequest concertDateRequest, final Pageable pageable);

}
