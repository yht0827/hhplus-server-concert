package kr.hhplus.be.server.domain.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;

	@Transactional
	public Payment paymentConcert(final PaymentConcertRequest paymentConcertRequest) {
		Payment payment = paymentConcertRequest.toEntity();

		boolean isPresent = paymentRepository.findByReservationId(paymentConcertRequest.reservationId())
			.isPresent();

		if (isPresent) {
			throw new CustomException(ErrorCode.PAYMENT_FINISHED);
		}
		return paymentRepository.save(payment);
	}
}
