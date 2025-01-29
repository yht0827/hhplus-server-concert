package kr.hhplus.be.server.application.token.port.out;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.token.entity.Token;

public record SignTokenResponse(String token, Long userId, LocalDateTime expiredAt) {

	public static SignTokenResponse toDto(final Token token, final String encryptedTokenId) {
		return new SignTokenResponse(encryptedTokenId, token.getUserId(), token.getExpiredAt());
	}
}
