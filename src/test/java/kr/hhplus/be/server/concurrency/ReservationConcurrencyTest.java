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

import kr.hhplus.be.server.application.ReservationFacade;
import kr.hhplus.be.server.common.DataCleaner;
import kr.hhplus.be.server.infrastructure.reservation.ReservationJpaRepository;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@DisplayName("좌석 예약 동시성 통합 테스트")
public class ReservationConcurrencyTest {

	@Autowired
	private ReservationJpaRepository reservationJpaRepository;

	@Autowired
	private ReservationFacade reservationFacade;

	@Autowired
	private DataCleaner dataCleaner;

	@BeforeEach
	void setUp() {
		dataCleaner.cleanUp();
	}

	@DisplayName("동시에 여러 요청이 와도 좌석 하나만 예약된다.")
	@Test
	void testConcurrencyReserveConcert() throws InterruptedException {
		int numberOfThreads = 10;

		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		for (int i = 0; i < numberOfThreads; i++) {
			ReserveRequest reserveRequest = ReserveRequest.builder()
				.userId((long)i + 1)
				.concertId(1L)
				.concertSeatId(1L)
				.seatNumber(1)
				.build();

			executorService.submit(() -> {
				try {
					reservationFacade.reserve(reserveRequest);
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
