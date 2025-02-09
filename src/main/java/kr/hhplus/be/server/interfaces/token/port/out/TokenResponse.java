package kr.hhplus.be.server.interfaces.token.port.out;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.token.port.out.TokenInfo;
import lombok.Builder;

@Builder
public record TokenResponse(
	@Schema(description = "암호화 된 유저 ID", example = "88LMpDHgHsdIa8Vx1JJ1xjMBME7vk0eH/dRJ9VMmuLgfkhFaQoYBupISOgbb0Rjj")
	String userId
) {
	public static TokenResponse toDto(final TokenInfo tokenInfo) {
		return TokenResponse.builder()
			.userId(tokenInfo.userId())
			.build();
	}
}
