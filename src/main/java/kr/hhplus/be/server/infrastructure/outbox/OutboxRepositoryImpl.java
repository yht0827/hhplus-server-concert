package kr.hhplus.be.server.infrastructure.outbox;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.outbox.entity.Outbox;
import kr.hhplus.be.server.domain.outbox.repository.OutboxRepository;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

	private final OutboxJpaRepository outboxJpaRepository;

	@Override
	public Outbox getByEventKey(String eventKey) {
		return outboxJpaRepository.findByEventKey(eventKey)
			.orElseThrow(() -> new CustomException(ErrorCode.OUTBOX_NOT_FOUND));
	}

	@Override
	public Outbox save(Outbox outbox) {
		return outboxJpaRepository.save(outbox);
	}

	@Override
	public List<Outbox> findAllAfterThreshold(int timeLimit, LocalDateTime now) {
		final LocalDateTime thresholdTime = now.minusMinutes(timeLimit);
		return outboxJpaRepository.findAllAfterThreshold(thresholdTime).stream()
			.toList();
	}
}
