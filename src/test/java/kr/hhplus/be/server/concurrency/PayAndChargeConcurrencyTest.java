package kr.hhplus.be.server.concurrency;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
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

import kr.hhplus.be.server.application.payment.facade.PaymentFacade;
import kr.hhplus.be.server.application.payment.port.in.ChargePointRequest;
import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.common.DataCleaner;
import kr.hhplus.be.server.domain.concert.entity.Concert;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository;
import kr.hhplus.be.server.infrastructure.point.PointJpaRepository;
import kr.hhplus.be.server.infrastructure.reservation.ReservationJpaRepository;
import kr.hhplus.be.server.infrastructure.token.TokenJpaRepository;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@DisplayName("결제 & 포인트 충전 동시성 통합 테스트")
public class PayAndChargeConcurrencyTest {

	@Autowired
	private DataCleaner dataCleaner;
	@Autowired
	private PointJpaRepository pointJpaRepository;
	@Autowired
	private ConcertJpaRepository concertJpaRepository;
	@Autowired
	private UserJpaRepository userJpaRepository;
	@Autowired
	private ReservationJpaRepository reservationJpaRepository;
	@Autowired
	private TokenJpaRepository tokenJpaRepository;
	@Autowired
	private PaymentFacade paymentFacade;

	@BeforeEach
	void setUp() {
		dataCleaner.cleanUp();
	}

	@DisplayName("동시에 포인트 충전 3회 수행")
	@Test
	void chargePointConcurrencyTest() throws InterruptedException {

		// given
		initData();

		int numberOfThreads = 5;
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		// when
		ChargePointRequest chargePointRequest = new ChargePointRequest(1L, 50000);

		for (int i = 0; i < numberOfThreads; i++) {
			PaymentRequest paymentRequest = new PaymentRequest((long)i + 1, 1L, 1L, 40000);

			executorService.submit(() -> {
				try {
					if (Math.random() > 0.5) {
						paymentFacade.chargePoint(chargePointRequest);
					} else {
						paymentFacade.paymentConcert(paymentRequest);
					}
					successCount.incrementAndGet();
				} catch (Exception e) {
					failCount.incrementAndGet();
				} finally {
					latch.countDown();
				}
			});
		}

		// 50000 40000 30000 -50000 -40000 -30000

		latch.await();
		executorService.shutdown();

		// then
		int balance = pointJpaRepository.findAll()
			.stream()
			.mapToInt(Point::getBalance)
			.sum();

		assertThat(balance).isEqualTo(30000);
		assertThat(successCount.get()).isEqualTo(numberOfThreads);
		assertThat(failCount.get()).isZero();
	}

	void initData() {
		User user = User.builder()
			.userName("홍길동")
			.build();

		userJpaRepository.save(user);

		Point point = Point.builder()
			.userId(1L)
			.balance(0)
			.build();

		pointJpaRepository.save(point);

		Token token = Token.builder()
			.userId(1L)
			.tokenStatus(Token.TokenStatus.ACTIVE)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();

		tokenJpaRepository.save(token);

		Concert concert1 = Concert.builder()
			.concertScheduleId(1L)
			.concertName("콘서트1")
			.seatCount(50)
			.concertPrice(40000)
			.build();

		Concert concert2 = Concert.builder()
			.concertScheduleId(2L)
			.concertName("콘서트2")
			.seatCount(50)
			.concertPrice(40000)
			.build();

		Concert concert3 = Concert.builder()
			.concertScheduleId(3L)
			.concertName("콘서트3")
			.seatCount(50)
			.concertPrice(40000)
			.build();

		concertJpaRepository.saveAll(List.of(concert1, concert2, concert3));

		Reservation reservation1 = Reservation.builder()
			.userId(1L)
			.reservationStatus(Reservation.ReservationStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();

		Reservation reservation2 = Reservation.builder()
			.userId(1L)
			.reservationStatus(Reservation.ReservationStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();

		Reservation reservation3 = Reservation.builder()
			.userId(1L)
			.reservationStatus(Reservation.ReservationStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();

		reservationJpaRepository.saveAll(List.of(reservation1, reservation2, reservation3));
	}

}
