package kr.hhplus.be.server.interfaces.token.port.out;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.token.port.out.TokenInfo;
import lombok.Builder;

@Builder
public record TokenResponse(
	@Schema(description = "토큰", example = "88LMpDHgHsdIa8Vx1JJ1xjMBME7vk0eH/dRJ9VMmuLgfkhFaQoYBupISOgbb0Rjj")
	String token,

	@Schema(description = "토큰 ID", example = "1")
	Long userId,

	@Schema(description = "토큰 만료 시간", example = "2024-01-01T12:00:00Z")
	LocalDateTime expiredAt
) {
	public static TokenResponse toDto(final TokenInfo tokenInfo) {
		return TokenResponse.builder()
			.token(tokenInfo.token())
			.userId(tokenInfo.userId())
			.expiredAt(tokenInfo.expiredAt())
			.build();
	}
}
