package kr.hhplus.be.server.payment.presentation.dto;

import lombok.Builder;

@Builder
public record ChargeResponse(String message, Integer balance) {
}
