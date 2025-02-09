package kr.hhplus.be.server.domain.token.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.token.port.in.TokenCommand;
import kr.hhplus.be.server.domain.token.repository.TokenRepository;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;

	public String createWaitToken(final TokenCommand tokenCommand) {
		String userId = String.valueOf(tokenCommand.userId());
		return tokenRepository.createWaitToken(userId);
	}

	public Long updateActiveToken() {
		List<String> waitTokens = tokenRepository.getAllWaitTokens();

		return tokenRepository.updateWaitTokens(waitTokens);
	}

	public void updateExpireToken(final Long userId) {
		String token = tokenRepository.removeActiveToken(String.valueOf(userId));

		if (StringUtils.isEmpty(token)) {
			throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
		}
	}

	public void checkValidToken(final String tokenId) {
		String activeToken = tokenRepository.getActiveToken(tokenId);

		if (StringUtils.isBlank(activeToken)) {
			throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
		}
	}

}
