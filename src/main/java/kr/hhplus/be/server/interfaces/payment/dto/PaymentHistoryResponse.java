package kr.hhplus.be.server.interfaces.payment.dto;

import lombok.Builder;

@Builder
public record PaymentHistoryResponse(Long paymentId, Integer paidPrice, String concertName, Integer concertSeat,
									 String concertDate) {

}
