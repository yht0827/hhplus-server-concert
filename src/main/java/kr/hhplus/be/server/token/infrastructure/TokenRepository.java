package kr.hhplus.be.server.token.infrastructure;

import kr.hhplus.be.server.token.domain.entity.Token;

public interface TokenRepository {

	Token save(Token token);
}
