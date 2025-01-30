package kr.hhplus.be.server.infrastructure.reservation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.reservation.entity.ReservationSeat;
import kr.hhplus.be.server.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

	private final ReservationJpaRepository reservationJpaRepository;
	private final ReservationSeatJpaRepository reservationSeatJpaRepository;

	@Override
	public Reservation save(final Reservation reservation) {
		return reservationJpaRepository.save(reservation);
	}

	@Override
	public Optional<Reservation> findById(final Long id) {
		return reservationJpaRepository.findById(id);
	}

	@Override
	public Optional<Reservation> findByConcertSeatId(final Long concertSeatId) {
		return reservationSeatJpaRepository.findByConcertSeatId(concertSeatId);
	}

	@Override
	public ReservationSeat save(final ReservationSeat reservationSeat) {
		return reservationSeatJpaRepository.save(reservationSeat);
	}

	@Override
	public List<Reservation> findAllByExpiredReservation() {
		return reservationJpaRepository.findAllByExpiredReservation();
	}

	@Override
	public Long updateCancelReservation(final List<Long> ids) {
		return reservationJpaRepository.updateCancelReservation(ids);
	}

}
