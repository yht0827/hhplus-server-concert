package kr.hhplus.be.server.interfaces.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.PaymentFacade;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertRequest;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentFacade paymentFacade;

	@PostMapping("/concert")
	public ResponseEntity<PaymentConcertResponse> paymentConcert(
		@RequestBody final PaymentConcertRequest paymentConcertRequest) {
		PaymentConcertResponse response = PaymentConcertResponse.toDto(
			paymentFacade.paymentConcert(paymentConcertRequest));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
