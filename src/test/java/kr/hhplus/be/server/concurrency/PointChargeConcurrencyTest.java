package kr.hhplus.be.server.concurrency;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import kr.hhplus.be.server.application.PointFacade;
import kr.hhplus.be.server.common.DataCleaner;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.infrastructure.point.PointJpaRepository;
import kr.hhplus.be.server.interfaces.point.dto.ChargeRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@DisplayName("포인트 충전 동시성 통합 테스트")
public class PointChargeConcurrencyTest {

	@Autowired
	private PointFacade pointFacade;

	@Autowired
	private PointJpaRepository pointJpaRepository;

	@Autowired
	private DataCleaner dataCleaner;

	@BeforeEach
	void setUp() {
		dataCleaner.cleanUp();
	}

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
		ChargeRequest chargeRequest = ChargeRequest.builder()
			.point(5000)
			.userId(1L)
			.build();

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.submit(() -> {
				try {
					pointFacade.chargePoint(chargeRequest);
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
