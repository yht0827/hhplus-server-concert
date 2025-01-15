package kr.hhplus.be.server.domain.token.repository;

import kr.hhplus.be.server.domain.token.entity.Token;

public interface TokenRepository {

	Token save(Token token);
}
