package kr.hhplus.be.server.infrastructure.reservation;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	public <T> void send(String topic, T message) {
		try {
			String json = objectMapper.writeValueAsString(message);
			kafkaTemplate.send(topic, json);
			log.info("Message sent to topic: {}, message: {}", topic, json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to serialize message", e);
		}
	}

	public <T> void send(String topic, String key, T message) {
		try {
			String json = objectMapper.writeValueAsString(message);
			kafkaTemplate.send(topic, key, json);
			log.info("Message sent to topic: {}, key: {}, message: {}", topic, key, json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to serialize message", e);
		}
	}

}
