package kr.hhplus.be.server.infrastructure.reservation;

import java.util.Optional;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public interface ReservationSeatCustomRepository {

	Optional<Reservation> findByConcertSeatId(final Long concertSeatId);
}
