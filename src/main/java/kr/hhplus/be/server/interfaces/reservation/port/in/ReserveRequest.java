package kr.hhplus.be.server.interfaces.reservation.port.in;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.reservation.port.in.ReserveSeatRequest;

public record ReserveRequest(
	@Schema(description = "사용자 ID", example = "1")
	Long userId,

	@Schema(description = "콘서트 좌석 ID", example = "1")
	Long concertSeatId,

	@Schema(description = "콘서트 좌석 번호", example = "1")
	Integer seatNumber
) {
	public ReserveSeatRequest toDto() {
		return new ReserveSeatRequest(userId, concertSeatId, seatNumber);
	}
}
