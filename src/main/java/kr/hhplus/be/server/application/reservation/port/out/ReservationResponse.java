package kr.hhplus.be.server.application.reservation.port.out;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public record ReservationResponse(Long reservationId, Long paymentId, LocalDateTime expiredAt) {

	public static ReservationResponse toDto(final Reservation reservation, final Payment payment) {
		return new ReservationResponse(reservation.getReservationId(), payment.getPaymentId(),
			reservation.getExpiredAt());
	}
}
