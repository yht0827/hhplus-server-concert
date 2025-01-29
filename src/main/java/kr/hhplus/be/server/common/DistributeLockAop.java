package kr.hhplus.be.server.common;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.common.annnotation.DistributeLock;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.common.util.CustomExpressionParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributeLockAop {

	private final RedissonClient redissonClient;
	private final AopTransaction aopForTransaction;
	private static final String REDISSON_LOCK_PREFIX = "LOCK:";

	@Around("@annotation(kr.hhplus.be.server.common.annnotation.DistributeLock)")
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
			return aopForTransaction.proceed(joinPoint);
		} catch (Exception e) {
			throw new CustomException(ErrorCode.DISTRIBUTE_LOCK_ERROR);
		} finally {
			rLock.unlock(); // 종료 시 락 해제
		}
	}
}
