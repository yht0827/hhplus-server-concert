package kr.hhplus.be.server.interfaces.payment.dto;

import kr.hhplus.be.server.domain.payment.entity.Payment;
import lombok.Builder;

@Builder
public record PaymentConcertRequest(Long userId, Long concertId, Long reservationId, Integer price) {

	public Payment toEntity() {
		return Payment.builder()
			.reservationId(this.reservationId)
			.concertId(this.concertId)
			.price(this.price)
			.build();
	}

}
