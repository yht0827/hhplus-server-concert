package kr.hhplus.be.server.domain.token.repository;

import java.util.List;

public interface TokenRepository {

	String createWaitToken(final String userId);

	List<String> getAllWaitTokens();

	Long updateWaitTokens(final List<String> tokens);

	String getActiveToken(final String userId);

	String removeActiveToken(final String userId);
}
