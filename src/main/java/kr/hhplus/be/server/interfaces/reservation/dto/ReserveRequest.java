package kr.hhplus.be.server.interfaces.reservation.dto;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public record ReserveRequest(Long userId, Long concertId, Integer seatNumber) {

	public Reservation toEntity(final Long concertSeatId) {
		return Reservation.builder()
			.concertSeatId(concertSeatId)
			.userId(this.userId)
			.status(Reservation.ReservationStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();
	}

	public ConcertSeat toEntity() {
		return ConcertSeat.builder()
			.seatNumber(this.seatNumber)
			.concertId(this.concertId)
			.build();
	}
}
