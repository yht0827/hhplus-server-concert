package kr.hhplus.be.server.interfaces.reservation.dto;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import lombok.Builder;

@Builder
public record ReserveRequest(Long userId, Long concertId, Long concertSeatId, Integer seatNumber) {

	public Reservation toReservationEntity() {
		return Reservation.builder()
			.concertSeatId(this.concertSeatId)
			.userId(this.userId)
			.status(Reservation.ReservationStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();
	}

	public ConcertSeat toConcertEntity() {
		return ConcertSeat.builder()
			.seatNumber(this.seatNumber)
			.concertId(this.concertId)
			.build();
	}
}
