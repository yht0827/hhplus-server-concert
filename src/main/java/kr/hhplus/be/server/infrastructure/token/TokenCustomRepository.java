package kr.hhplus.be.server.infrastructure.token;

import java.util.List;

import kr.hhplus.be.server.domain.token.entity.Token;

public interface TokenCustomRepository {

	List<Token> getAllWaitTokens();

	List<Token> getTimeoutTokens();

	Long updateWaitTokens(final List<Long> ids);

	Long updateExpireTokens(final List<Long> ids);

	Token findByUserId(final Long userId);
}
