package kr.hhplus.be.server.token.interfaces.dto;

import lombok.Builder;

@Builder
public record TokenRequest(Long userId) {
}
