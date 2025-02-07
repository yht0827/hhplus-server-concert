package kr.hhplus.be.server.infrastructure.token;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.ZSetOperations;

import kr.hhplus.be.server.support.BaseIntegrationTest;

@DisplayName("redis 토큰 테스트")
public class TokenRedisRepositoryTest extends BaseIntegrationTest {

	@DisplayName("redis 토큰 발급(대기열)")
	@Test
	void tokenCreateTest() {
		// given
		String token = "1";

		//when
		String waitToken = tokenRepository.createWaitToken(token);

		//then
		assertThat(waitToken).isEqualTo(token);
	}

	@DisplayName("redis 토큰 활성화")
	@Test
	void activeTokenUpdateTest() {
		// given
		String token = "1";
		String WAIT_QUEUE = "waitQueue";
		redisTemplate.opsForZSet().add(WAIT_QUEUE, token, System.currentTimeMillis());
		List<String> waitTokens = Objects.requireNonNull(redisTemplate.opsForZSet()
				.popMin(WAIT_QUEUE, 50))
			.stream()
			.map(ZSetOperations.TypedTuple::getValue)
			.toList();

		//when
		Long activeTokenCount = tokenRepository.updateWaitTokens(waitTokens);

		//then
		assertThat(activeTokenCount).isOne();
	}

	@DisplayName("redis 토큰 만료")
	@Test
	void expiredTokenUpdateTest() {
		// given
		String token = "1";
		String ACTIVE_TOKEN = "active:";
		int ACTIVE_TOKEN_TIME = 10;
		redisTemplate.opsForValue().set(ACTIVE_TOKEN + token, token, ACTIVE_TOKEN_TIME, TimeUnit.MINUTES);

		//when
		tokenRepository.removeActiveToken(token);

		//then
		String activeToken = redisTemplate.opsForValue().get(ACTIVE_TOKEN + token);
		assertThat(activeToken).isNull();
	}
}
