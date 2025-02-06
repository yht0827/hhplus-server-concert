package kr.hhplus.be.server.support;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import kr.hhplus.be.server.application.payment.facade.PaymentFacade;
import kr.hhplus.be.server.application.reservation.facade.ReservationFacade;
import kr.hhplus.be.server.domain.token.repository.TokenRepository;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository;
import kr.hhplus.be.server.infrastructure.payment.PaymentJpaRepository;
import kr.hhplus.be.server.infrastructure.point.PointJpaRepository;
import kr.hhplus.be.server.infrastructure.reservation.ReservationJpaRepository;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;

@Import(TestContainerConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

	@Autowired
	protected PointJpaRepository pointJpaRepository;

	@Autowired
	protected ConcertJpaRepository concertJpaRepository;

	@Autowired
	protected UserJpaRepository userJpaRepository;

	@Autowired
	protected ReservationJpaRepository reservationJpaRepository;

	@Autowired
	protected PaymentFacade paymentFacade;

	@Autowired
	protected PaymentJpaRepository paymentJpaRepository;

	@Autowired
	protected ReservationFacade reservationFacade;

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected TokenService tokenService;

	@Autowired
	protected TokenRepository tokenRepository;

	@Autowired
	private DataCleaner dataCleaner;

	@BeforeEach
	void setUp() {
		dataCleaner.cleanUp();
		tokenRepository.flushAll();
	}
}
