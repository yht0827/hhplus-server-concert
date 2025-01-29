package kr.hhplus.be.server.infrastructure.concert;

import static kr.hhplus.be.server.domain.concert.entity.QConcertSeat.*;
import static kr.hhplus.be.server.domain.concert.entity.QConcertSeatCategory.*;
import static kr.hhplus.be.server.domain.reservation.entity.QReservation.*;
import static kr.hhplus.be.server.domain.reservation.entity.QReservationSeat.*;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcertSeatCustomRepositoryImpl implements ConcertSeatCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Integer getConcertPrice(final Long concertSeatId) {
		return jpaQueryFactory.select(concertSeatCategory.price)
			.from(concertSeatCategory)
			.innerJoin(concertSeat).on(concertSeat.concertSeatCategoryId.eq(concertSeatCategory.concertSeatCategoryId))
			.where(concertSeat.concertSeatId.eq(concertSeatId))
			.fetchOne();
	}

	@Override
	public List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids) {
		return jpaQueryFactory.select(
				Projections.constructor(ConcertResponse.ConcertSeatInfoResponse.class, concertSeat.seatNumber,
					concertSeat.concertId))
			.from(concertSeat)
			.innerJoin(reservationSeat).on(reservationSeat.concertSeatId.eq(concertSeat.concertSeatId))
			.innerJoin(reservation).on(reservation.reservationId.eq(reservationSeat.reservationId))
			.where(concertSeat.concertId.in(ids),
				reservation.reservationStatus.eq(Reservation.ReservationStatus.RESERVED))
			.fetch();
	}

	@Override
	public Optional<ConcertSeat> findReservedConcertSeat(final Long concertId, final Integer seatNumber) {

		return Optional.ofNullable(jpaQueryFactory.selectFrom(concertSeat)
			.innerJoin(reservationSeat).on(reservationSeat.concertSeatId.eq(concertSeat.concertSeatId))
			.innerJoin(reservation).on(reservation.reservationId.eq(reservationSeat.reservationId))
			.where(reservation.reservationStatus.eq(Reservation.ReservationStatus.RESERVED),
				concertSeat.concertId.eq(concertId), concertSeat.seatNumber.eq(seatNumber))
			.fetchOne());
	}

	@Override
	public ConcertSeat findConcertSeat(final Long concertId, final Integer seatNumber) {
		return jpaQueryFactory.selectFrom(concertSeat)
			.where(concertSeat.concertId.eq(concertId), concertSeat.seatNumber.eq(seatNumber))
			.fetchOne();
	}

}
