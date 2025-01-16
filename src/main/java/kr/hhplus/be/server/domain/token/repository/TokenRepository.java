package kr.hhplus.be.server.domain.token.repository;

import java.util.List;

import kr.hhplus.be.server.domain.token.entity.Token;

public interface TokenRepository {

	Token save(final Token token);

	List<Token> getAllWaitTokens();

	List<Token> getTimeoutTokens();

	Long updateWaitTokens(final List<Long> ids);

	Long updateExpireTokens(final List<Long> ids);

	Token findByUserId(final Long userId);
}
