package kr.hhplus.be.server.interfaces.payment.dto;

import lombok.Builder;

@Builder
public record ChargeResponse(String message, Integer balance) {
}
