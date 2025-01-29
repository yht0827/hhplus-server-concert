package kr.hhplus.be.server.interfaces.token.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.interfaces.token.port.in.TokenRequest;
import kr.hhplus.be.server.interfaces.token.port.out.TokenResponse;

@Tag(name = "Token", description = "토큰 API")
public interface TokenAPI {
	@Operation(summary = "콘서트 좌석 예약 대기열 토큰 발급", description = "콘서트 좌석 예약에 필요한 대기열 토큰 발급")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "대기열 토큰 반환 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))})
	ResponseEntity<TokenResponse> create(@RequestBody final TokenRequest tokenRequest);
}
