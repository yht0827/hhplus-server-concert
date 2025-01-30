package kr.hhplus.be.server.domain.payment.service;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;

	public Payment paymentConcert(final PaymentRequest paymentRequest) {
		Payment payment = paymentRepository.findByReservationId(paymentRequest.reservationId())
			.orElseThrow(() -> new CustomException(ErrorCode.PAYMENT_NOT_FOUND));

		payment.updateStatus(Payment.PaymentStatus.COMPLETED);

		return payment;
	}

	public Payment createPayment(final Reservation reservation, final Integer price) {
		Payment payment = Payment.toEntity(reservation.getReservationId(), price, Payment.PaymentStatus.PENDING,
			Payment.PaymentType.POINT);

		return paymentRepository.save(payment);
	}

}
