package kr.hhplus.be.server.domain.outbox.repository;

import java.time.LocalDateTime;
import java.util.List;

import kr.hhplus.be.server.domain.outbox.entity.Outbox;

public interface OutboxRepository {

	Outbox getByEventKey(String eventKey);

	Outbox save(Outbox outbox);

	List<Outbox> findAllAfterThreshold(int timeLimit, LocalDateTime now);
}
