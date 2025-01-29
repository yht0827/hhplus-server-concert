package kr.hhplus.be.server.interfaces.reservation.port.out;

import java.time.LocalDateTime;

import kr.hhplus.be.server.application.reservation.port.out.ReservationResponse;
import lombok.Builder;

@Builder
public record ReserveResponse(Long reservationId, Long paymentId, LocalDateTime expiredAt) {

	public static ReserveResponse toDto(final ReservationResponse reservationResponse) {
		return ReserveResponse.builder()
			.reservationId(reservationResponse.reservationId())
			.paymentId(reservationResponse.paymentId())
			.expiredAt(reservationResponse.expiredAt())
			.build();
	}

}
