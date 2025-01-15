package kr.hhplus.be.server.interfaces.payment.dto;

import lombok.Builder;

@Builder
public record ChargeRequest(String uuid, Integer point) {
}
