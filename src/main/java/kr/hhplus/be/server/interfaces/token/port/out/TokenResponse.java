package kr.hhplus.be.server.interfaces.token.port.out;

import java.time.LocalDateTime;

import kr.hhplus.be.server.application.token.port.out.SignTokenResponse;
import lombok.Builder;

@Builder
public record TokenResponse(String token, Long userId, LocalDateTime expiredAt) {
	public static TokenResponse toDto(final SignTokenResponse signTokenResponse) {
		return TokenResponse.builder()
			.token(signTokenResponse.token())
			.userId(signTokenResponse.userId())
			.expiredAt(signTokenResponse.expiredAt())
			.build();
	}
}
