package kr.hhplus.be.server.interfaces.reservation.dto;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import lombok.Builder;

@Builder
public record ReserveResponse(Long reservationId, LocalDateTime expiredAt) {

	public static ReserveResponse toDto(final Reservation reservation) {
		return ReserveResponse.builder()
			.reservationId(reservation.getReservationId())
			.expiredAt(reservation.getExpiredAt())
			.build();
	}

}
