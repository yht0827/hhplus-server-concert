package kr.hhplus.be.server.interfaces.payment.dto;

import kr.hhplus.be.server.domain.payment.entity.Payment;
import lombok.Builder;

@Builder
public record PaymentConcertResponse(Long paymentId, Integer price) {

	public static PaymentConcertResponse toDto(final Payment payment) {
		return PaymentConcertResponse.builder()
			.paymentId(payment.getPaymentId())
			.price(payment.getPrice())
			.build();
	}
}
