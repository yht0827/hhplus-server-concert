package kr.hhplus.be.server.support;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import kr.hhplus.be.server.application.payment.facade.PaymentFacade;
import kr.hhplus.be.server.application.reservation.facade.ReservationFacade;
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository;
import kr.hhplus.be.server.infrastructure.payment.PaymentJpaRepository;
import kr.hhplus.be.server.infrastructure.point.PointJpaRepository;
import kr.hhplus.be.server.infrastructure.reservation.ReservationJpaRepository;
import kr.hhplus.be.server.infrastructure.token.TokenJpaRepository;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;

public abstract class ServiceIntegrationTest {

	@Autowired
	protected PointJpaRepository pointJpaRepository;

	@Autowired
	protected ConcertJpaRepository concertJpaRepository;

	@Autowired
	protected UserJpaRepository userJpaRepository;

	@Autowired
	protected ReservationJpaRepository reservationJpaRepository;

	@Autowired
	protected TokenJpaRepository tokenJpaRepository;

	@Autowired
	protected PaymentFacade paymentFacade;

	@Autowired
	protected PaymentJpaRepository paymentJpaRepository;

	@Autowired
	protected ReservationFacade reservationFacade;

	@Autowired
	private DataCleaner dataCleaner;

	@BeforeEach
	void setUp() {
		dataCleaner.cleanUp();
	}
}
