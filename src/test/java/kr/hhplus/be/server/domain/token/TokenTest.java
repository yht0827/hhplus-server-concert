package kr.hhplus.be.server.domain.token;

import static org.assertj.core.api.Assertions.*;
import static org.instancio.Select.*;

import java.time.LocalDateTime;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.domain.token.entity.Token;

@DisplayName("토큰 단위 테스트")
public class TokenTest {

	@Test
	@DisplayName("토큰 객체 생성")
	void createTokenEntityTest() {
		// given
		Long tokenId = 1L;
		Long userId = 1L;
		Token.TokenStatus status = Token.TokenStatus.WAIT;
		LocalDateTime expiredAt = LocalDateTime.of(2025, 2, 5, 0, 0, 0);

		// when
		Token token = Instancio.of(Token.class)
			.set(field(Token::getTokenId), tokenId)
			.set(field(Token::getUserId), userId)
			.set(field(Token::getTokenStatus), status)
			.set(field(Token::getExpiredAt), expiredAt)
			.create();

		// then
		assertThat(token.getTokenId()).isEqualTo(tokenId);
		assertThat(token.getUserId()).isEqualTo(userId);
		assertThat(token.getTokenStatus()).isEqualTo(status);
		assertThat(token.getExpiredAt()).isEqualTo(expiredAt);
	}

	@Test
	@DisplayName("토큰 생성")
	void createTokenTest() {
		// given
		Long userId = 1L;

		// when
		Token token = Token.createToken(userId);

		// then
		assertThat(token.getUserId()).isEqualTo(userId);
		assertThat(token.getTokenStatus()).isEqualTo(Token.TokenStatus.WAIT);
	}

	@Test
	@DisplayName("토큰 만료 처리")
	void updateTokenTest() {
		// given
		Token token = Instancio.of(Token.class)
			.set(field(Token::getTokenStatus), Token.TokenStatus.ACTIVE)
			.create();

		// when
		token.updateExpiredStatus();

		// then
		assertThat(token.getTokenStatus()).isEqualTo(Token.TokenStatus.EXPIRED);
	}
}
