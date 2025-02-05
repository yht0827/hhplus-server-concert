package kr.hhplus.be.server.infrastructure.reservation;

import static kr.hhplus.be.server.domain.concert.entity.QConcertSeat.*;
import static kr.hhplus.be.server.domain.reservation.entity.QReservation.*;
import static kr.hhplus.be.server.domain.reservation.entity.QReservationSeat.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationSeatCustomRepositoryImpl implements ReservationSeatCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Reservation> findByConcertSeatId(final Long concertSeatId) {
		return Optional.ofNullable(jpaQueryFactory.selectFrom(reservation)
			.innerJoin(reservationSeat).on(reservationSeat.reservationId.eq(reservation.reservationId))
			.innerJoin(concertSeat).on(concertSeat.concertSeatId.eq(reservationSeat.reservationSeatId))
			.where(concertSeat.concertSeatId.eq(concertSeatId),
				reservation.reservationStatus.ne(Reservation.ReservationStatus.CANCELLED),
				reservation.expiredAt.after(LocalDateTime.now()))
			.fetchOne());
	}
}
