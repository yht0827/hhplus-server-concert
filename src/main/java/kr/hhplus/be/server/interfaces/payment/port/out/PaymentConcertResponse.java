package kr.hhplus.be.server.interfaces.payment.port.out;

import kr.hhplus.be.server.application.payment.port.out.PaymentResponse;
import lombok.Builder;

@Builder
public record PaymentConcertResponse(Long paymentId, Integer price) {

	public static PaymentConcertResponse toDto(final PaymentResponse paymentResponse) {
		return PaymentConcertResponse.builder()
			.paymentId(paymentResponse.paymentId())
			.price(paymentResponse.price())
			.build();
	}
}
