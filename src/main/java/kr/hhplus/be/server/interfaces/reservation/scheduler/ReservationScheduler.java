package kr.hhplus.be.server.interfaces.reservation.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationScheduler {

	private final ReservationService reservationService;

	@Scheduled(cron = "0 * * * * *")
	public void processActiveTokens() {
		Long size = reservationService.updateExpiredReservation();
		log.info("취소된 콘서트 예약 개수: {}", size);
	}

}
