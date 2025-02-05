package kr.hhplus.be.server.interfaces.point.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.interfaces.point.port.in.ChargeRequest;
import kr.hhplus.be.server.interfaces.point.port.out.ChargeResponse;
import kr.hhplus.be.server.interfaces.point.port.out.PointResponse;

@Tag(name = "Point", description = "포인트 API")
public interface PointAPI {

	@Operation(summary = "포인트 조회", description = "포인트를 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "포인트 조회 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = PointResponse.class)))})
	ResponseEntity<PointResponse> getPoint(@PathVariable final Long pointId);

	@Operation(summary = "포인트 충전", description = "포인트를 충전합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "포인트 충전 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ChargeResponse.class)))})
	ResponseEntity<ChargeResponse> chargePoint(@RequestBody final ChargeRequest chargeRequest);
}
