package kr.hhplus.be.server.infrastructure.concert;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;

public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeat, Long>, ConcertSeatCustomRepository {
}
