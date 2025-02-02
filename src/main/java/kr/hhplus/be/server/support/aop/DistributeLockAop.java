package kr.hhplus.be.server.support.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.support.annnotation.DistributeLock;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;
import kr.hhplus.be.server.support.util.CustomExpressionParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DistributeLockAop {

	private final RedissonClient redissonClient;
	private static final String REDISSON_LOCK_PREFIX = "LOCK:";

	@Around("@annotation(kr.hhplus.be.server.support.annnotation.DistributeLock)")
	public Object distributeLock(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

		String key =
			REDISSON_LOCK_PREFIX + CustomExpressionParser.parseKey(signature.getParameterNames(), joinPoint.getArgs(),
				distributeLock.key());

		RLock rLock = redissonClient.getLock(key);
		try {
			boolean acquireLock = rLock.tryLock(distributeLock.waitTime(), distributeLock.leaseTime(),
				distributeLock.timeUnit()); // 락 획득 시도
			if (!acquireLock) {
				return false;
			}
			log.info("get lock success {}", key);
			return joinPoint.proceed();
		} catch (Exception e) {
			throw new CustomException(ErrorCode.DISTRIBUTE_LOCK_ERROR);
		} finally {
			rLock.unlock(); // 종료 시 락 해제
		}
	}
}
