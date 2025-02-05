package kr.hhplus.be.server.interfaces.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.payment.facade.PaymentFacade;
import kr.hhplus.be.server.application.payment.port.out.PaymentInfo;
import kr.hhplus.be.server.interfaces.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.interfaces.payment.port.out.PaymentResponse;
import kr.hhplus.be.server.support.annnotation.AuthorizationToken;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController implements PaymentAPI {

	private final PaymentFacade paymentFacade;

	@PostMapping("/concert")
	@AuthorizationToken
	public ResponseEntity<PaymentResponse> paymentConcert(
		@RequestBody final PaymentRequest paymentRequest) {
		PaymentInfo paymentInfo = paymentFacade.paymentConcert(paymentRequest.toDto());

		PaymentResponse response = PaymentResponse.toDto(paymentInfo);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
