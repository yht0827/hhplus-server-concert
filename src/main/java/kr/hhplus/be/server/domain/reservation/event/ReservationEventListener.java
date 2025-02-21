package kr.hhplus.be.server.domain.reservation.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.hhplus.be.server.domain.outbox.service.OutboxService;
import kr.hhplus.be.server.infrastructure.reservation.ReservationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationEventListener {

	private final OutboxService outboxService;
	private final ReservationProducer reservationProducer;

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void saveOutbox(final ReservedEvent event) throws JsonProcessingException {
		outboxService.createOutbox(event.getClass().getSimpleName(), event.eventKey(), event);
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handle(final ReservedEvent event) {
		reservationProducer.send("concert.concert-reserved", event.eventKey(), event);
	}
}
