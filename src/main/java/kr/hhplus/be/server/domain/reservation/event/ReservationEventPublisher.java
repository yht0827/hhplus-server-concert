package kr.hhplus.be.server.domain.reservation.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationEventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	public void publish(final ReservedEvent event) {
		applicationEventPublisher.publishEvent(event);
	}
}
