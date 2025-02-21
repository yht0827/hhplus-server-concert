package kr.hhplus.be.server.interfaces.outbox.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.hhplus.be.server.domain.outbox.entity.Outbox;
import kr.hhplus.be.server.domain.outbox.service.OutboxService;
import kr.hhplus.be.server.infrastructure.reservation.ReservationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxScheduler {

	private final OutboxService outboxService;
	private final ObjectMapper objectMapper;
	private final ReservationProducer reservationProducer;

	@Scheduled(fixedRate = 60000)
	public void reprocessInitState() {
		log.info("Outbox 대기 중인 이벤트 발송 스케줄러 실행");
		final List<Outbox> outboxes = outboxService.findAllAfterThreshold(5);
		outboxes.forEach(outbox -> {
			try {
				final Object event = objectMapper.readValue(outbox.getPayload(), Object.class);
				reservationProducer.send("concert.concert-reserved", outbox.getEventKey(), event);
			} catch (Exception e) {
				log.error("Outbox 이벤트 발송 중 오류 발생 (ID: {}): {}", outbox.getOutboxId(), e.getMessage());
			}
		});
	}

}
