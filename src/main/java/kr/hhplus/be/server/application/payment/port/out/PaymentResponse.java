package kr.hhplus.be.server.application.payment.port.out;

import kr.hhplus.be.server.domain.payment.entity.Payment;

public record PaymentResponse(Long paymentId, Integer price) {

	public static PaymentResponse toDto(final Payment payment) {
		return new PaymentResponse(payment.getPaymentId(), payment.getPrice());
	}
}
