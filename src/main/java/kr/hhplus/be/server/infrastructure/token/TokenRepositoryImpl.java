package kr.hhplus.be.server.infrastructure.token;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.token.repository.TokenRepository;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

	private final RedisTemplate<String, String> redisTemplate;
	private static final String WAIT_QUEUE = "waitQueue";
	private static final String ACTIVE_TOKEN = "active:";
	private static final Integer ACTIVE_TOKEN_TIME = 10;
	private static final Integer MAX_TOKEN_NUMBER = 50;

	public TokenRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public String createWaitToken(final String userId) {
		redisTemplate.opsForZSet().add(WAIT_QUEUE, userId, System.currentTimeMillis());
		return userId;
	}

	@Override
	public List<String> getAllWaitTokens() {
		return Optional.ofNullable(redisTemplate.opsForZSet()
				.popMin(WAIT_QUEUE, MAX_TOKEN_NUMBER))
			.stream()
			.flatMap(Set::stream)
			.map(ZSetOperations.TypedTuple::getValue)
			.toList();
	}

	@Override
	public Long updateWaitTokens(final List<String> tokens) {
		tokens.forEach(token -> redisTemplate.opsForValue()
			.set(ACTIVE_TOKEN + token, token, ACTIVE_TOKEN_TIME, TimeUnit.MINUTES));

		return (long)tokens.size();
	}

	@Override
	public String getActiveToken(final String userId) {
		return redisTemplate.opsForValue()
			.get(ACTIVE_TOKEN + userId);
	}

	@Override
	public String removeActiveToken(final String userId) {
		return redisTemplate.opsForValue()
			.getAndDelete(ACTIVE_TOKEN + userId);
	}

	@Override
	public void flushAll() {
		Objects.requireNonNull(redisTemplate.getConnectionFactory())
			.getConnection()
			.serverCommands()
			.flushAll();
	}

}
