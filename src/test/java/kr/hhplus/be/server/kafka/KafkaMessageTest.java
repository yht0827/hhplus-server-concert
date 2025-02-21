package kr.hhplus.be.server.kafka;

import static org.assertj.core.api.Assertions.*;
import static org.testcontainers.shaded.org.awaitility.Awaitility.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import kr.hhplus.be.server.infrastructure.reservation.ReservationProducer;
import kr.hhplus.be.server.support.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaMessageTest extends BaseIntegrationTest {

	@Autowired
	private ReservationProducer reservationProducer;
	private static final String TOPIC = "test-topic";
	private final AtomicInteger receivedMessageCount = new AtomicInteger(0);

	@Test
	void kafkaMessageTest() {
		int messageCount = 50;

		for (int i = 0; i < messageCount; i++) {

			reservationProducer.send(TOPIC, "Kafka Test!!! " + i);
		}

		await()
			.atMost(5, TimeUnit.SECONDS)
			.untilAsserted(() -> assertThat(receivedMessageCount.get()).isEqualTo(messageCount));
	}

	// 발행 확인을 위한 리스너 설정
	@KafkaListener(topics = TOPIC, groupId = "test-group")
	public void consumeTestMessage(String message) {
		log.info("Received message: {}", message);
		receivedMessageCount.incrementAndGet();
	}
}
