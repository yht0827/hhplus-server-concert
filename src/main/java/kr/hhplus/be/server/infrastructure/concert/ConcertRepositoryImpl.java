package kr.hhplus.be.server.infrastructure.concert;

import kr.hhplus.be.server.domain.concert.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

    private ConcertJpaRepository concertJpaRepository;
    private ConcertScheduleJpaRepository concertScheduleJpaRepository;
    private SeatJpaRepository seatJpaRepository;


}
