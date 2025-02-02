package kr.hhplus.be.server.payment.application;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.support.BaseIntegrationTest;
import kr.hhplus.be.server.domain.concert.entity.Concert;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.user.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@DisplayName("결제 동시성 통합 테스트")
public class PaymentConcurrencyTest extends BaseIntegrationTest {

	@DisplayName("동시에 여러 요청이 와도 결제는 한번만 성공한다.")
	@Test
	void paymentConcurrencyTest() throws InterruptedException {
		// given
		initData();

		int numberOfThreads = 5;

		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		// when
		PaymentRequest paymentRequest = new PaymentRequest(1L, 1L, 1L, 50000);

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.submit(() -> {
				try {
					paymentFacade.paymentConcert(paymentRequest);

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
		long count = paymentJpaRepository.findAll().size();
		assertThat(count).isEqualTo(1);
		assertThat(successCount.get()).isOne();
		assertThat(failCount.get()).isEqualTo(numberOfThreads - 1);
	}

	void initData() {
		Concert concert1 = Concert.builder()
			.concertScheduleId(1L)
			.concertName("콘서트1")
			.seatCount(50)
			.concertPrice(50000)
			.build();

		concertJpaRepository.save(concert1);

		User user = User.builder()
			.userName("홍길동")
			.build();

		userJpaRepository.save(user);

		Point point = Point.builder()
			.userId(1L)
			.balance(100000)
			.build();

		pointJpaRepository.save(point);

		Reservation reservation = Reservation.builder()
			.userId(1L)
			.reservationStatus(Reservation.ReservationStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();

		reservationJpaRepository.save(reservation);

		Token token = Token.builder()
			.userId(1L)
			.tokenStatus(Token.TokenStatus.ACTIVE)
			.expiredAt(LocalDateTime.now().plusMinutes(5))
			.build();

		tokenJpaRepository.save(token);
	}
}
