package kr.hhplus.be.server.interfaces.token.dto;

import kr.hhplus.be.server.domain.token.entity.Token;
import lombok.Builder;

@Builder
public record TokenResponse(Long tokenId) {
	public static TokenResponse toDto(Token token) {
		return TokenResponse.builder()
			.tokenId(token.getTokenId())
			.build();
	}
}
