package kr.hhplus.be.server.domain.token.repository;

import kr.hhplus.be.server.domain.token.entity.Token;

import java.util.List;

public interface TokenRepository {

    Token save(Token token);

    List<Token> getAllWaitTokens();

    List<Token> getTimeoutTokens();

    Long updateWaitTokens(List<Long> ids);

    Long updateExpireTokens(List<Long> ids);
}
