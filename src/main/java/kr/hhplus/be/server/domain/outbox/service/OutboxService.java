package kr.hhplus.be.server.domain.outbox.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.hhplus.be.server.domain.outbox.entity.Outbox;
import kr.hhplus.be.server.domain.outbox.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutboxService {

	private final OutboxRepository outboxRepository;
	private final ObjectMapper objectMapper;

	@Transactional
	public void createOutbox(final String eventType, final String eventKey, final Object payload)
		throws JsonProcessingException {
		final String payloadJson = objectMapper.writeValueAsString(payload);
		final Outbox outbox = Outbox.create(eventType, eventKey, payloadJson);
		outboxRepository.save(outbox);
	}

	@Transactional
	public void publish(final String eventKey) {
		Outbox outbox = outboxRepository.getByEventKey(eventKey);
		outbox.publish();
	}

	@Transactional(readOnly = true)
	public List<Outbox> findAllAfterThreshold(final Integer timeLimit) {
		return outboxRepository.findAllAfterThreshold(timeLimit, LocalDateTime.now());
	}

}
