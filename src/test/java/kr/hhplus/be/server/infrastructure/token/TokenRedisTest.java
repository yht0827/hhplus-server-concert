package kr.hhplus.be.server.infrastructure.token;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.support.BaseIntegrationTest;

@DisplayName("redis 토큰 테스트")
public class TokenRedisTest extends BaseIntegrationTest {

	@DisplayName("redis 토큰 발급(대기열)")
	@Test
	void tokenCreateTest() {
		// given
		String token = "1";
		tokenRepository.createWaitToken(token);

		//when
		Integer waitTokenCount = tokenRepository.countWaitToken();

		//then
		assertThat(waitTokenCount).isEqualTo(1);
	}

	@DisplayName("redis 토큰 활성화")
	@Test
	void activeTokenUpdateTest() {
		// given
		String token = "1";
		tokenRepository.createWaitToken(token);

		//when
		List<String> waitTokens = tokenRepository.getAllWaitTokens();
		tokenRepository.updateWaitTokens(waitTokens);

		//then
		String activeToken = tokenRepository.getActiveToken(token);
		assertThat(activeToken).isEqualTo("value:1");
	}

	@DisplayName("redis 토큰 만료")
	@Test
	void expiredTokenUpdateTest() {
		// given
		String token = "1";
		tokenRepository.createWaitToken(token);
		List<String> waitTokens = tokenRepository.getAllWaitTokens();
		tokenRepository.updateWaitTokens(waitTokens);

		//when
		tokenRepository.removeActiveToken(token);

		//then
		String activeToken = tokenRepository.getActiveToken(token);
		assertThat(activeToken).isNull();
	}
}
