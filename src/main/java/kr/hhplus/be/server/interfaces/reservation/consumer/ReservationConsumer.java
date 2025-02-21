package kr.hhplus.be.server.interfaces.reservation.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.hhplus.be.server.domain.outbox.entity.Outbox;
import kr.hhplus.be.server.domain.outbox.service.OutboxService;
import kr.hhplus.be.server.domain.reservation.event.DataPlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReservationConsumer {

	private final OutboxService outboxService;
	private final DataPlatformService dataPlatformService;
	private final ObjectMapper objectMapper;

	@KafkaListener(groupId = "outbox-group", topics = "concert.concert-reserved")
	public void consumeOutbox(ConsumerRecord<String, String> data, Acknowledgment acknowledgment,
		Consumer<String, String> consumer) {
		final String eventKey = data.key();
		outboxService.publish(eventKey);
		acknowledgment.acknowledge();
	}

	@KafkaListener(groupId = "external-platform-group", topics = "concert.concert-reserved")
	public void consumeExternalPlatform(ConsumerRecord<String, String> data, Acknowledgment acknowledgment,
		Consumer<String, String> consumer) throws JsonProcessingException {
		Outbox outbox = objectMapper.readValue(data.value(), Outbox.class);
		final String payload = outbox.getPayload();
		dataPlatformService.sendReservationData(payload);
		acknowledgment.acknowledge();
	}
}
