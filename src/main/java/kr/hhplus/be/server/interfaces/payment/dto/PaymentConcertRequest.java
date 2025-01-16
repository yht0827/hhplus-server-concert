package kr.hhplus.be.server.interfaces.payment.dto;

import kr.hhplus.be.server.domain.payment.entity.Payment;

public record PaymentConcertRequest(Long userId, Long concertId, Long reservationId, Integer price) {

	public Payment toEntity(final Integer concertPrice) {
		return Payment.builder()
			.reservationId(this.reservationId)
			.concertId(this.concertId)
			.price(concertPrice)
			.build();
	}

}
