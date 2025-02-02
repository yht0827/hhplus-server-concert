package kr.hhplus.be.server.domain.token.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.token.port.in.TokenCommand;
import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.repository.TokenRepository;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	private static final Integer MAX_TOKEN_COUNT = 50;

	public Token createToken(final TokenCommand tokenCommand) {
		Token token = Token.createToken(tokenCommand.userId());

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
		List<Long> timeoutTokenIdList = tokenRepository.getTimeoutTokens()
			.stream()
			.map(Token::getTokenId)
			.toList();

		return tokenRepository.updateExpireTokens(timeoutTokenIdList);
	}

	public void updateExpireToken(final Long userId) {
		Token token = tokenRepository.findByUserId(userId);

		if (token == null) {
			throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
		}

		token.updateExpiredStatus();
	}

	@Transactional(readOnly = true)
	public void checkValidToken(final Long tokenId) {
		Token token = tokenRepository.findByTokenId(tokenId)
			.orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND));

		if (token.getTokenStatus() == Token.TokenStatus.EXPIRED) {
			throw new CustomException(ErrorCode.INVALID_TOKEN);
		}
	}

}
