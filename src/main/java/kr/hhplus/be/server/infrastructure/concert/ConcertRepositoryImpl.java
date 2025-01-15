package kr.hhplus.be.server.infrastructure.concert;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.hhplus.be.server.domain.concert.repository.ConcertRepository;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.hhplus.be.server.domain.concert.entity.QConcert.concert;
import static kr.hhplus.be.server.domain.concert.entity.QConcertSchedule.concertSchedule;
import static kr.hhplus.be.server.domain.concert.entity.QConcertSeat.concertSeat;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;
    private final ConcertScheduleJpaRepository concertScheduleJpaRepository;
    private final ConcertSeatJpaRepository concertSeatJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ConcertResponse.ConcertDateResponse> getAvailableConcertList() {

        return jpaQueryFactory
                .select(Projections.constructor(ConcertResponse.ConcertDateResponse.class, concert.concertId, concert.concertName,
                        concertSchedule.concertDate, concertSchedule.concertTime, concert.seatCount))
                .from(concert)
                .innerJoin(concert)
                .on(concertSchedule.concertScheduleId.eq(concert.concertScheduleId))
                .fetch();
    }

    @Override
    public List<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date) {
        return jpaQueryFactory
                .select(Projections.fields(ConcertResponse.ConcertSeatResponse.class,
                        concert.concertId.as("concertId"), concert.concertName.as("concertName"),
                        concertSchedule.concertTime.as("concertTime"), concert.seatCount.as("seatCount")))
                .from(concert)
                .innerJoin(concert).on(concertSchedule.concertScheduleId.eq(concert.concertScheduleId))
                .where(concertSchedule.concertDate.eq(date))
                .fetch();
    }

    @Override
    public List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids) {
        return jpaQueryFactory
                .select(Projections.constructor(ConcertResponse.ConcertSeatInfoResponse.class,
                        concertSeat.seatNumber, concertSeat.concertId))
                .from(concertSeat)
                .where(concertSeat.concertId.in(ids))
                .fetch();
    }

}
