package kr.hhplus.be.server.infrastructure.concert;

import static kr.hhplus.be.server.domain.concert.entity.QConcert.*;
import static kr.hhplus.be.server.domain.concert.entity.QConcertSchedule.*;

import java.util.List;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcertCustomRepositoryImpl implements ConcertCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private final EntityManager entityManager;

	@Override
	public List<ConcertResponse.ConcertDateResponse> getAvailableConcertList() {
		return jpaQueryFactory.select(
				Projections.constructor(ConcertResponse.ConcertDateResponse.class, concert.concertId, concert.concertName,
					concertSchedule.scheduleDate, concertSchedule.concertStartTime, concertSchedule.concertEndTime,
					concert.seatCount))
			.from(concert)
			.innerJoin(concertSchedule)
			.on(concertSchedule.concertScheduleId.eq(concert.concertScheduleId))
			.fetch();
	}

	@Override
	public List<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date) {
		return jpaQueryFactory.select(
				Projections.fields(ConcertResponse.ConcertSeatResponse.class, concert.concertId,
					concert.concertName, concertSchedule.concertStartTime, concertSchedule.concertEndTime,
					concert.seatCount))
			.from(concert)
			.innerJoin(concertSchedule)
			.on(concertSchedule.concertScheduleId.eq(concert.concertScheduleId))
			.where(concertSchedule.scheduleDate.eq(date))
			.fetch();
	}

	@Override
	public Long decreaseConcertSeatNumber(final Long concertId) {
		long count = jpaQueryFactory.update(concert)
			.set(concert.seatCount, -1)
			.where(concert.concertId.eq(concertId))
			.execute();

		entityManager.flush();
		entityManager.clear();

		return count;
	}

}
