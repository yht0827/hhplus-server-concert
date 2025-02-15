package kr.hhplus.be.server.domain.reservation.event;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataPlatformService {

	public void sendReservationData(final Long reservationId) {

		try {
			Thread.sleep(10000);
			// kafka
		} catch (InterruptedException e) {
			log.error("Failed to send reservation data to data platform", e);
		}
	}

}
