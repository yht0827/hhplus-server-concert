package kr.hhplus.be.server.interfaces.reservation.port.out;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.reservation.port.out.ReserveInfo;
import lombok.Builder;

@Builder
public record ReserveResponse(
	@Schema(description = "예약 ID", example = "1")
	Long reservationId,

	@Schema(description = "결제 ID", example = "1")
	Long paymentId,

	@Schema(description = "예약 만료 시간", example = "2024-01-01T12:00:00Z")
	LocalDateTime expiredAt
) {
	public static ReserveResponse toDto(final ReserveInfo reserveInfo) {
		return ReserveResponse.builder()
			.reservationId(reserveInfo.reservationId())
			.paymentId(reserveInfo.paymentId())
			.expiredAt(reserveInfo.expiredAt())
			.build();
	}

}
