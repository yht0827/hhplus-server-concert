package kr.hhplus.be.server.infrastructure.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.domain.token.entity.Token;

public interface TokenJpaRepository extends JpaRepository<Token, Long>, TokenCustomRepository {

	Optional<Token> findByTokenId(Long tokenId);
}
