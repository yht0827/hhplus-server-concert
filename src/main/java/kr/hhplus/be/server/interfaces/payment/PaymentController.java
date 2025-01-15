package kr.hhplus.be.server.interfaces.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertRequest;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertResponse;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentHistoryResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

	@PostMapping("/concert")
	public ResponseEntity<PaymentConcertResponse> paymentConcert(
		@RequestBody PaymentConcertRequest paymentConcertRequest) {

		return ResponseEntity.ok(null);
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<PaymentHistoryResponse> getPaymentHistory(@PathVariable("paymentId") Long paymentId) {

		return ResponseEntity.ok(null);
	}
}
