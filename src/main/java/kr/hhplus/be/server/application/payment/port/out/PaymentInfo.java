package kr.hhplus.be.server.application.payment.port.out;

import kr.hhplus.be.server.domain.payment.entity.Payment;

public record PaymentInfo(Long paymentId, Integer price) {

	public static PaymentInfo toDto(final Payment payment) {
		return new PaymentInfo(payment.getPaymentId(), payment.getPrice());
	}
}
