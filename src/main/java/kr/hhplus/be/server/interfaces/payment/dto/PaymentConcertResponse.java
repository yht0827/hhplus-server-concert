package kr.hhplus.be.server.interfaces.payment.dto;

import lombok.Builder;

@Builder
public record PaymentConcertResponse(String message, Long paymentId) {
}
