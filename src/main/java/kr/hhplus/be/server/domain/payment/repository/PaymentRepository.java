package kr.hhplus.be.server.domain.payment.repository;

import java.util.Optional;

import kr.hhplus.be.server.domain.payment.entity.Payment;

public interface PaymentRepository {

	Payment save(final Payment payment);

	Optional<Payment> findByReservationId(final Long reservationId);
}
