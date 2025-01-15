package kr.hhplus.be.server.infrastructure.concert;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeat, Long> {
}
