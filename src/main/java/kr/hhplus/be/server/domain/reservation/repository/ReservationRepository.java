package kr.hhplus.be.server.domain.reservation.repository;

import java.util.Optional;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public interface ReservationRepository {

	Reservation save(final Reservation reservation);

	Optional<Reservation> findById(final Long id);
}
