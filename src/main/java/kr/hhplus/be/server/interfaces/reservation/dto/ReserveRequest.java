package kr.hhplus.be.server.interfaces.reservation.dto;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public record ReserveRequest(Long concertSeatId, Long userId, Integer seatNumber) {

	public Reservation toEntity() {
		return Reservation.builder()
			.concertSeatId(this.concertSeatId)
			.userId(this.userId)
			.status(Reservation.ReservationStatus.WAIT)
			.build();
	}
}
