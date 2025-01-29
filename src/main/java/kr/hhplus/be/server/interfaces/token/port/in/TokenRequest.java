package kr.hhplus.be.server.interfaces.token.port.in;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.token.port.in.SignTokenRequest;
import lombok.Builder;

@Builder
public record TokenRequest(
	@Schema(description = "사용자 ID", example = "1")
	Long userId
) {
	public SignTokenRequest toDto() {
		return new SignTokenRequest(userId);
	}
}
