package kr.hhplus.be.server.interfaces.payment.dto;

import lombok.Builder;

@Builder
public record SearchPointResponse(Integer balance) {
}
