package kr.hhplus.be.server.payment.presentation.dto;

import lombok.Builder;

@Builder
public record ChargeRequest(String uuid, Integer point) {
}
