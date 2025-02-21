package kr.hhplus.be.server.domain.reservation.event;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataPlatformService {

	public void sendReservationData(final String reservationId) {

		try {
			Thread.sleep(100);

		} catch (InterruptedException e) {
			log.error("Failed to send reservation data to data platform", e);
		}
	}

}
