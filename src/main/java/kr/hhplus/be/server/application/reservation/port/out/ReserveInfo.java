package kr.hhplus.be.server.application.reservation.port.out;

import java.time.LocalDateTime;

import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public record ReserveInfo(Long reservationId, Long paymentId, LocalDateTime expiredAt) {

	public static ReserveInfo toDto(final Reservation reservation, final Payment payment) {
		return new ReserveInfo(reservation.getReservationId(), payment.getPaymentId(),
			reservation.getExpiredAt());
	}
}
