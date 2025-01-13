package kr.hhplus.be.server.token.infrastructure;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.token.domain.entity.Token;
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
