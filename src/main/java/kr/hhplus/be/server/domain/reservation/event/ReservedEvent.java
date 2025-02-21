package kr.hhplus.be.server.domain.reservation.event;

import java.util.UUID;

public record ReservedEvent(Long reservationId, String eventKey) {

	public ReservedEvent(Long reservationId) {
		this(reservationId, UUID.randomUUID().toString());
	}
}
