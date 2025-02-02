package kr.hhplus.be.server.application.reservation.port.in;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public record ReserveCommand(Long userId, Long concertSeatId, Integer seatNumber) {

	public Reservation toReservationEntity() {
		return Reservation.builder()
			.userId(this.userId)
			.reservationStatus(Reservation.ReservationStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();
	}
}
