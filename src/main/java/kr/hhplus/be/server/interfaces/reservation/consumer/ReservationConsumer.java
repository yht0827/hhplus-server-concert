package kr.hhplus.be.server.interfaces.reservation.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReservationConsumer {

	@KafkaListener(groupId = "myGroup", topics = "test-topic")
	public void test(ConsumerRecord<String, String> data, Acknowledgment acknowledgment,
		Consumer<String, String> consumer) {
		log.info("Consumed message: topic={}, partition={}, offset={}, key={}, value={}",
			data.topic(), data.partition(), data.offset(), data.key(), data.value());
		acknowledgment.acknowledge();
	}
}
