package kr.hhplus.be.server.token.presentation.dto;

import lombok.Builder;

@Builder
public record TokenRequest(String uuid) {
}
