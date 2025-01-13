package kr.hhplus.be.server.token.interfaces.dto;

import kr.hhplus.be.server.token.domain.entity.Token;
import lombok.Builder;

@Builder
public record TokenResponse(Long tokenId) {
	public static TokenResponse toDto(Token token) {
		return TokenResponse.builder()
			.tokenId(token.getTokenId())
			.build();
	}
}
