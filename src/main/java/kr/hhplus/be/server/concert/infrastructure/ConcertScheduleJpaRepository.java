package kr.hhplus.be.server.concert.infrastructure;

import kr.hhplus.be.server.concert.domain.entity.ConcertSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertScheduleJpaRepository extends JpaRepository<ConcertSchedule, Long> {
}
