package kr.hhplus.be.server.concert.infrastructure;

import kr.hhplus.be.server.concert.domain.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {
}
