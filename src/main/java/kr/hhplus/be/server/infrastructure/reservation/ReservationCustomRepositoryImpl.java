package kr.hhplus.be.server.infrastructure.reservation;

import static kr.hhplus.be.server.domain.reservation.entity.QReservation.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationCustomRepositoryImpl implements ReservationCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private final EntityManager entityManager;

	@Override
	public List<Reservation> findAllByExpiredReservation() {
		return jpaQueryFactory.selectFrom(reservation)
			.where(reservation.expiredAt.after(LocalDateTime.now()))
			.fetch();
	}

	@Override
	public Long updateCancelReservation(final List<Long> ids) {
		long count = jpaQueryFactory.update(reservation)
			.set(reservation.reservationStatus, Reservation.ReservationStatus.CANCELLED)
			.where(reservation.reservationId.in(ids))
			.execute();

		entityManager.flush();
		entityManager.clear();

		return count;
	}
}
