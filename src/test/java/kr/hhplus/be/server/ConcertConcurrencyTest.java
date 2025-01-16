package kr.hhplus.be.server;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import kr.hhplus.be.server.application.ReservationFacade;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.infrastructure.reservation.ReservationJpaRepository;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class ConcertConcurrencyTest {

	@Autowired
	private ReservationJpaRepository reservationJpaRepository;

	@Autowired
	private ReservationFacade reservationFacade;

	@BeforeEach
	void tearDown() {

	}

	@DisplayName("동시에 여러 요청이 와도 콘서트 하나만 예약된다.")
	@Test
	void testConcurrencyReserveConcert() throws InterruptedException {
		int numberOfThreads = 10;

		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);

		for (int i = 0; i < numberOfThreads; i++) {
			ReserveRequest reserveRequest = ReserveRequest.builder()
				.userId((long)i + 1)
				.concertId(2L)
				.seatNumber(1)
				.build();

			executorService.submit(() -> {
				try {
					reservationFacade.reserve(reserveRequest);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();
		executorService.shutdown();

		List<Reservation> reservations = reservationJpaRepository.findAll();
		assertEquals(1, reservations.size());
	}

}
