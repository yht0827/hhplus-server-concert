package kr.hhplus.be.server.payment.presentation.dto;

import lombok.Builder;

@Builder
public record PaymentConcertResponse(String message, Long paymentId) {
}
