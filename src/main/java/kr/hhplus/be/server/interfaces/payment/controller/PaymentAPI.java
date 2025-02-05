package kr.hhplus.be.server.interfaces.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.interfaces.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.interfaces.payment.port.out.PaymentResponse;

@Tag(name = "Payment", description = "좌석 API")
public interface PaymentAPI {

	@Operation(summary = "예약 좌석 결제", description = "예약한 좌석을 결제 합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "좌석 결제 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class)))})
	ResponseEntity<PaymentResponse> paymentConcert(
		@RequestBody final PaymentRequest paymentRequest);
}
