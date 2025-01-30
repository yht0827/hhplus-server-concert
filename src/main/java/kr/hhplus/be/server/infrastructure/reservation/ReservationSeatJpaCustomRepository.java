package kr.hhplus.be.server.infrastructure.reservation;

import java.util.Optional;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public interface ReservationSeatJpaCustomRepository {

	Optional<Reservation> findByConcertSeatId(final Long concertSeatId);
}
