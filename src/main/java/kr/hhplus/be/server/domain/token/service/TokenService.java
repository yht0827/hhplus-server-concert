package kr.hhplus.be.server.domain.token.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.repository.TokenRepository;
import kr.hhplus.be.server.interfaces.token.dto.TokenRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	private static final Integer MAX_TOKEN_COUNT = 50;

	@Transactional
	public Token createToken(final TokenRequest tokenRequest) {
		Token token = Token.createToken(tokenRequest.userId());

		return tokenRepository.save(token);
	}

	@Transactional
	public Long updateActiveToken() {
		List<Long> waitTokenIdList = tokenRepository.getAllWaitTokens()
			.stream()
			.limit(MAX_TOKEN_COUNT)
			.map(Token::getTokenId)
			.toList();

		return tokenRepository.updateWaitTokens(waitTokenIdList);
	}

	@Transactional
	public Long updateExpireToken() {
		List<Long> timeoutTokenIdList = tokenRepository.getTimeoutTokens().stream().map(Token::getTokenId).toList();

		return tokenRepository.updateExpireTokens(timeoutTokenIdList);
	}

	@Transactional
	public void updateExpireToken(final Long userId) {
		Token token = tokenRepository.findByUserId(userId);

		if (token == null) {
			throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
		}

		token.updateExpiredStatus();
	}

}
