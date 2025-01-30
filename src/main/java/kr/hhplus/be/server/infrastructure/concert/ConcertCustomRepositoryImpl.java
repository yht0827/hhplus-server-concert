package kr.hhplus.be.server.infrastructure.concert;

import static kr.hhplus.be.server.domain.concert.entity.QConcert.*;
import static kr.hhplus.be.server.domain.concert.entity.QConcertSchedule.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcertCustomRepositoryImpl implements ConcertCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private final EntityManager entityManager;

	@Override
	public Page<ConcertResponse.ConcertDateResponse> getAvailableConcertList(final Pageable pageable) {
		List<ConcertResponse.ConcertDateResponse> concertDateResponseList = jpaQueryFactory.select(
				Projections.constructor(ConcertResponse.ConcertDateResponse.class, concert.concertId, concert.concertName,
					concertSchedule.scheduleDate, concertSchedule.concertStartTime, concertSchedule.concertEndTime,
					concert.seatCount))
			.from(concert)
			.innerJoin(concertSchedule)
			.on(concertSchedule.concertScheduleId.eq(concert.concertScheduleId))
			.where(concert.seatCount.gt(0))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		JPAQuery<Long> count = jpaQueryFactory.select(concert.count())
			.from(concert)
			.innerJoin(concertSchedule)
			.on(concertSchedule.concertScheduleId.eq(concert.concertScheduleId))
			.where(concert.seatCount.gt(0));

		return PageableExecutionUtils.getPage(concertDateResponseList, pageable, count::fetchOne);
	}

	@Override
	public Page<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date,
		final Pageable pageable) {
		List<ConcertResponse.ConcertSeatResponse> concertSeatResponseList = jpaQueryFactory.select(
				Projections.fields(ConcertResponse.ConcertSeatResponse.class, concert.concertId,
					concert.concertName, concertSchedule.concertStartTime, concertSchedule.concertEndTime,
					concert.seatCount))
			.from(concert)
			.innerJoin(concertSchedule)
			.on(concertSchedule.concertScheduleId.eq(concert.concertScheduleId))
			.where(concertSchedule.scheduleDate.eq(date))
			.fetch();

		JPAQuery<Long> count = jpaQueryFactory.select(concert.count())
			.from(concert)
			.innerJoin(concertSchedule)
			.on(concertSchedule.concertScheduleId.eq(concert.concertScheduleId))
			.where(concertSchedule.scheduleDate.eq(date));

		return PageableExecutionUtils.getPage(concertSeatResponseList, pageable, count::fetchOne);
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
