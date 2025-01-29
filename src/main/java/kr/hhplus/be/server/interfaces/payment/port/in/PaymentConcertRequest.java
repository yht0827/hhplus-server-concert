package kr.hhplus.be.server.interfaces.payment.port.in;

import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import lombok.Builder;

@Builder
public record PaymentConcertRequest(Long reservationId, Long userId, Long concertId, Integer price) {

	public PaymentRequest toDto() {
		return new PaymentRequest(reservationId, userId, concertId, price);
	}
}
