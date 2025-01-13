package kr.hhplus.be.server.token.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record TokenRequest(@Schema(description = "사용자 ID") Long userId) {
}
