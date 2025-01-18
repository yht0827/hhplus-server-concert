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

import kr.hhplus.be.server.application.PointFacade;
import kr.hhplus.be.server.application.ReservationFacade;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.repository.PointRepository;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository;
import kr.hhplus.be.server.infrastructure.point.PointHistoryJpaRepository;
import kr.hhplus.be.server.infrastructure.point.PointJpaRepository;
import kr.hhplus.be.server.infrastructure.reservation.ReservationJpaRepository;
import kr.hhplus.be.server.interfaces.point.dto.ChargeRequest;
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

	@Autowired
	private ConcertJpaRepository concertJpaRepository;

	@Autowired
	private PointFacade pointFacade;

	@Autowired
	private PointJpaRepository pointJpaRepository;

	@Autowired
	private PointRepository pointRepository;

	@Autowired
	private PointHistoryJpaRepository pointHistoryJpaRepository;

	@BeforeEach
	void tearDown() {
		reservationJpaRepository.deleteAllInBatch();
		pointJpaRepository.deleteAllInBatch();
		pointHistoryJpaRepository.deleteAllInBatch();
	}

	@DisplayName("동시에 포인트 충전")
	@Test
	void testConcurrencyChargePoint() throws InterruptedException {
		int numberOfThreads = 10;

		Point point = Point.builder()
			.userId(1L)
			.balance(0)
			.build();

		pointJpaRepository.save(point);

		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);

		ChargeRequest chargeRequest = ChargeRequest.builder()
			.point(5000)
			.userId(1L)
			.build();

		for (int i = 0; i < numberOfThreads; i++) {

			executorService.submit(() -> {
				try {
					pointFacade.chargePoint(chargeRequest);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();
		executorService.shutdown();

		int balance = pointJpaRepository.findAll()
			.stream()
			.mapToInt(Point::getBalance)
			.sum();

		assertEquals(50000, balance);
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
				.concertId(1L)
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
