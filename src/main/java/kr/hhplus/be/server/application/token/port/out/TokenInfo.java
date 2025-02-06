package kr.hhplus.be.server.application.token.port.out;

public record TokenInfo(String userId) {

	public static TokenInfo toDto(final String encryptTokenId) {
		return new TokenInfo(encryptTokenId);
	}
}
