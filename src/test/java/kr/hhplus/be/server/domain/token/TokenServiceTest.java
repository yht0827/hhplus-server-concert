package kr.hhplus.be.server.domain.token;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.application.token.port.in.TokenCommand;
import kr.hhplus.be.server.support.BaseIntegrationTest;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;

@DisplayName("토큰 service 테스트")
public class TokenServiceTest extends BaseIntegrationTest {

	@Test
	@DisplayName("redis 대기 토큰 발급")
	void createWaitTokenTest() {
		// given
		TokenCommand tokenCommand = new TokenCommand(1L);

		// when
		String waitToken = tokenService.createWaitToken(tokenCommand);

		// then
		assertThat(waitToken).isEqualTo(tokenCommand.userId().toString());
	}

	@Test
	@DisplayName("redis 토큰 활성화처리 테스트")
	void updateActiveTokenTest() {
		// given
		String token = "1";
		redisTemplate.opsForZSet().add("waitQueue", token, System.currentTimeMillis());

		// when
		tokenService.updateActiveToken();

		// then
		String activeToken = redisTemplate.opsForValue().get("active:" + token);
		assertThat(activeToken).isEqualTo(token);
	}

	@Test
	@DisplayName("redis 토큰 만료처리 테스트")
	void updateExpireToken() {
		// given
		Long token = 1L;
		redisTemplate.opsForValue().set("active:" + token, String.valueOf(token), 10, TimeUnit.MINUTES);

		// when
		tokenService.updateExpireToken(token);

		// then
		String activeToken = redisTemplate.opsForValue().get("active:" + token);
		assertThat(activeToken).isNull();

	}

	@Test
	@DisplayName("redis 활성화 토큰 체크 테스트 - 에러")
	void checkValidTokenErrorTest() {
		// given
		String token = "2";

		// when
		CustomException customException = assertThrows(CustomException.class,
			() -> tokenService.checkValidToken(token));

		// then
		assertThat(customException.getMessage()).isEqualTo(ErrorCode.TOKEN_NOT_FOUND.getMessage());
	}
}
