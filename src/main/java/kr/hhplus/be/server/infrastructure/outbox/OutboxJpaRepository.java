package kr.hhplus.be.server.infrastructure.outbox;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.lettuce.core.dynamic.annotation.Param;
import kr.hhplus.be.server.domain.outbox.entity.Outbox;

public interface OutboxJpaRepository extends JpaRepository<Outbox, Long> {

	@Query("""
		SELECT o
		FROM Outbox o
		WHERE o.outboxStatus = Outbox.OutboxStatus.INIT
		AND o.createdAt < :thresholdTime
		"""
	)
	List<Outbox> findAllAfterThreshold(@Param("thresholdTime") LocalDateTime thresholdTime);

	Optional<Outbox> findByEventKey(final String eventKey);
}
