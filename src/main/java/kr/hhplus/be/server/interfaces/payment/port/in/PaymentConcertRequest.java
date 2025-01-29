package kr.hhplus.be.server.interfaces.payment.port.in;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import lombok.Builder;

@Builder
public record PaymentConcertRequest(
	@Schema(description = "예약 ID", example = "1")
	Long reservationId,

	@Schema(description = "사용자 ID", example = "1")
	Long userId,

	@Schema(description = "콘서트 ID", example = "1")
	Long concertId,

	@Schema(description = "콘서트 좌석 가격", example = "50000")
	Integer price
) {
	public PaymentRequest toDto() {
		return new PaymentRequest(reservationId, userId, concertId, price);
	}
}
