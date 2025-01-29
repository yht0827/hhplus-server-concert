package kr.hhplus.be.server.domain.reservation.repository;

import java.util.List;
import java.util.Optional;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.reservation.entity.ReservationSeat;

public interface ReservationRepository {

	Reservation save(final Reservation reservation);

	Optional<Reservation> findById(final Long id);

	Optional<Reservation> findByConcertSeatId(final Long concertSeatId);

	ReservationSeat save(final ReservationSeat reservationSeat);

	List<Reservation> findAllByExpiredReservation();

	Long updateCancelReservation(final List<Long> ids);
}
