package kr.hhplus.be.server.interfaces.reservation.port.in;

import kr.hhplus.be.server.application.reservation.port.in.ReserveSeatRequest;

public record ReserveRequest(Long userId, Long concertSeatId, Integer seatNumber) {

	public ReserveSeatRequest toDto() {
		return new ReserveSeatRequest(userId, concertSeatId, seatNumber);
	}
}
