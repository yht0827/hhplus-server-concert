package kr.hhplus.be.server.interfaces.reservation.dto;

import lombok.Builder;

@Builder
public record ReserveResponse(String message, Long reserveId) {
}
