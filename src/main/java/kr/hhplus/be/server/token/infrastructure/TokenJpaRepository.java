package kr.hhplus.be.server.token.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.token.domain.entity.Token;

public interface TokenJpaRepository extends JpaRepository<Token, Long> {
}
