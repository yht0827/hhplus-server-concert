package kr.hhplus.be.server.application.token.port.out;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.token.entity.Token;

public record TokenInfo(String token, Long userId, LocalDateTime expiredAt) {

	public static TokenInfo toDto(final Token token, final String encryptedTokenId) {
		return new TokenInfo(encryptedTokenId, token.getUserId(), token.getExpiredAt());
	}
}
