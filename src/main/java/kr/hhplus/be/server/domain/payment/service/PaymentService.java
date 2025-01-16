package kr.hhplus.be.server.domain.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;

	@Transactional
	public Payment paymentConcert(final PaymentConcertRequest paymentConcertRequest, final Integer concertPrice) {
		Payment payment = paymentConcertRequest.toEntity(concertPrice);

		return paymentRepository.save(payment);
	}
}
