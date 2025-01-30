package kr.hhplus.be.server.infrastructure.token;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

	private final TokenJpaRepository tokenJpaRepository;

	@Override
	public Token save(final Token token) {
		return tokenJpaRepository.save(token);
	}

	@Override
	public List<Token> getAllWaitTokens() {
		return tokenJpaRepository.getAllWaitTokens();
	}

	@Override
	public List<Token> getTimeoutTokens() {
		return tokenJpaRepository.getTimeoutTokens();
	}

	@Override
	public Long updateWaitTokens(final List<Long> ids) {
		return tokenJpaRepository.updateWaitTokens(ids);
	}

	@Override
	public Long updateExpireTokens(final List<Long> ids) {
		return tokenJpaRepository.updateExpireTokens(ids);
	}

	@Override
	public Token findByUserId(final Long userId) {
		return tokenJpaRepository.findByUserId(userId);
	}

	@Override
	public Optional<Token> findByTokenId(final Long tokenId) {
		return tokenJpaRepository.findByTokenId(tokenId);
	}
}
