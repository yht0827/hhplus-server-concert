package kr.hhplus.be.server.domain.outbox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.server.support.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "outbox")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Outbox extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "outbox_id")
	private Long outboxId;

	@Column(name = "event_key")
	private String eventKey;

	@Column(name = "event_type")
	private String eventType;

	private String payload;

	@Enumerated(EnumType.STRING)
	@Column(name = "outbox_status")
	private OutboxStatus outboxStatus;

	public enum OutboxStatus {
		INIT, SUCCESS
	}

	@Builder
	public Outbox(Long outboxId, String eventKey, String eventType, String payload, OutboxStatus outboxStatus) {
		this.outboxId = outboxId;
		this.eventKey = eventKey;
		this.eventType = eventType;
		this.payload = payload;
		this.outboxStatus = outboxStatus;
	}

	public static Outbox create(String eventKey, String eventType, String payload) {
		return Outbox.builder()
			.eventKey(eventKey)
			.eventType(eventType)
			.payload(payload)
			.outboxStatus(OutboxStatus.INIT)
			.build();
	}

	public void publish() {
		this.outboxStatus = OutboxStatus.SUCCESS;
	}
}
