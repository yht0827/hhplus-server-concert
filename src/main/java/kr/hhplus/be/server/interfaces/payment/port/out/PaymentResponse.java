package kr.hhplus.be.server.interfaces.payment.port.out;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.payment.port.out.PaymentInfo;
import lombok.Builder;

@Builder
public record PaymentResponse(
	@Schema(description = "결제 ID", example = "1")
	Long paymentId,

	@Schema(description = "결제 가격", example = "10000")
	Integer price
) {
	public static PaymentResponse toDto(final PaymentInfo paymentInfo) {
		return PaymentResponse.builder()
			.paymentId(paymentInfo.paymentId())
			.price(paymentInfo.price())
			.build();
	}
}
