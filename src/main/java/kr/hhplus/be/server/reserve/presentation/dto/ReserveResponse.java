package kr.hhplus.be.server.reserve.presentation.dto;

import lombok.Builder;

@Builder
public record ReserveResponse(String message, Long reserveId) {
}
