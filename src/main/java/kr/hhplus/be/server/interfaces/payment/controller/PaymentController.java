package kr.hhplus.be.server.interfaces.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.payment.facade.PaymentFacade;
import kr.hhplus.be.server.application.payment.port.out.PaymentResponse;
import kr.hhplus.be.server.support.annnotation.AuthorizationToken;
import kr.hhplus.be.server.interfaces.payment.port.in.PaymentConcertRequest;
import kr.hhplus.be.server.interfaces.payment.port.out.PaymentConcertResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController implements PaymentAPI {

	private final PaymentFacade paymentFacade;

	@PostMapping("/concert")
	@AuthorizationToken
	public ResponseEntity<PaymentConcertResponse> paymentConcert(
		@RequestBody final PaymentConcertRequest paymentConcertRequest) {
		PaymentResponse paymentResponse = paymentFacade.paymentConcert(paymentConcertRequest.toDto());

		PaymentConcertResponse response = PaymentConcertResponse.toDto(paymentResponse);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
