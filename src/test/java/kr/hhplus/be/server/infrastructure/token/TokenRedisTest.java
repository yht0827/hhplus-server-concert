package kr.hhplus.be.server.infrastructure.token;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.application.token.port.in.TokenCommand;
import kr.hhplus.be.server.support.BaseIntegrationTest;

@DisplayName("redis 토큰 테스트")
public class TokenRedisTest extends BaseIntegrationTest {

	@DisplayName("redis 토큰 발급(대기열)")
	@Test
	void tokenCreateTest() {
		// given
		TokenCommand tokenCommand = new TokenCommand(1L);
		tokenService.createWaitToken(tokenCommand);

		//when
		Integer waitTokenCount = tokenRepository.countWaitToken();

		//then
		assertThat(waitTokenCount).isEqualTo(1);
	}

	@DisplayName("redis 토큰 활성화")
	@Test
	void activeTokenUpdateTest() {
		// given
		String userId = "1";
		TokenCommand tokenCommand = new TokenCommand(1L);
		tokenService.createWaitToken(tokenCommand);

		//when
		tokenService.updateActiveToken();
		String token = tokenRepository.getActiveToken(userId);

		//then
		String activeToken = "value:1";
		assertThat(token).isEqualTo(activeToken);
	}

	@DisplayName("redis 토큰 만료")
	@Test
	void expiredTokenUpdateTest() {
		// given
		TokenCommand tokenCommand = new TokenCommand(1L);
		tokenService.createWaitToken(tokenCommand);
		tokenService.updateActiveToken();

		//when
		tokenService.updateExpireToken(1L);

		//then
		Integer token = tokenRepository.countWaitToken();
		assertThat(token).isZero();
	}
}
