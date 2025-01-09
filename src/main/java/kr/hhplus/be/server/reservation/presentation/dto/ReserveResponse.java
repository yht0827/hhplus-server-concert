package kr.hhplus.be.server.reservation.presentation.dto;

import lombok.Builder;

@Builder
public record ReserveResponse(String message, Long reserveId) {
}
