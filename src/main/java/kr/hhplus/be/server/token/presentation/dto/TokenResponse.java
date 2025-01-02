package kr.hhplus.be.server.token.presentation.dto;

import lombok.Builder;

@Builder
public record TokenResponse(String message, Long tokenId) {
}
