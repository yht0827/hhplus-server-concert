package kr.hhplus.be.server.application.reservation;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.application.reservation.port.in.ReserveCommand;
import kr.hhplus.be.server.support.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisplayName("좌석 예약 동시성 통합 테스트")
public class ReservationConcurrencyTest extends BaseIntegrationTest {

	@DisplayName("동시에 여러 요청이 와도 좌석 하나만 예약된다.")
	@Test
	void testConcurrencyReserveConcert() throws InterruptedException {
		int numberOfThreads = 10;

		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		for (int i = 0; i < numberOfThreads; i++) {
			ReserveCommand reserveCommand = new ReserveCommand(((long)i + 1), 1L, 1);

			executorService.submit(() -> {
				try {
					reservationFacade.reserve(reserveCommand);
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

		long count = reservationJpaRepository.findAll().size();

		assertThat(count).isEqualTo(1);
		assertThat(successCount.get()).isOne();
		assertThat(failCount.get()).isEqualTo(numberOfThreads - 1);
	}

}
