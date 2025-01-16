package kr.hhplus.be.server.domain.reservation.repository;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public interface ReservationRepository {

	Reservation save(final Reservation reservation);

}
