package kr.hhplus.be.server.application.point;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.application.payment.port.in.ChargeCommand;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.support.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisplayName("포인트 충전 동시성 통합 테스트")
public class PointChargeConcurrencyTest extends BaseIntegrationTest {

	@DisplayName("동시에 포인트 충전 10회 수행")
	@Test
	void chargePointConcurrencyTest() throws InterruptedException {

		// given
		Point point = Point.builder()
			.userId(1L)
			.balance(0)
			.build();

		pointJpaRepository.save(point);

		int numberOfThreads = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		// when
		ChargeCommand chargeCommand = new ChargeCommand(1L, 5000);

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.submit(() -> {
				try {
					paymentFacade.chargePoint(chargeCommand);
					successCount.incrementAndGet();
				} catch (Exception e) {
					failCount.incrementAndGet();
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();
		executorService.shutdown();

		// then
		int balance = pointJpaRepository.findAll()
			.stream()
			.mapToInt(Point::getBalance)
			.sum();

		assertThat(balance).isEqualTo(50000);
		assertThat(successCount.get()).isEqualTo(numberOfThreads);
		assertThat(failCount.get()).isZero();
	}
}
