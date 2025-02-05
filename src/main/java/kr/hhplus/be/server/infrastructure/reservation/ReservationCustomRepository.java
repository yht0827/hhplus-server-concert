package kr.hhplus.be.server.infrastructure.reservation;

import java.util.List;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public interface ReservationCustomRepository {

	List<Reservation> findAllByExpiredReservation();

	Long updateCancelReservation(final List<Long> ids);
}
