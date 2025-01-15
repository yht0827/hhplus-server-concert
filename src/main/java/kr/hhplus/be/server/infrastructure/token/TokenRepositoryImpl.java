package kr.hhplus.be.server.infrastructure.token;

import kr.hhplus.be.server.domain.token.repository.TokenRepository;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.domain.token.entity.Token;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

	private final TokenJpaRepository tokenJpaRepository;

	@Override
	public Token save(Token token) {
		return tokenJpaRepository.save(token);
	}

}
