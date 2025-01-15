package kr.hhplus.be.server.interfaces.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.interfaces.payment.dto.ChargeRequest;
import kr.hhplus.be.server.interfaces.payment.dto.ChargeResponse;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertRequest;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentConcertResponse;
import kr.hhplus.be.server.interfaces.payment.dto.PaymentHistoryResponse;
import kr.hhplus.be.server.interfaces.payment.dto.SearchPointRequest;
import kr.hhplus.be.server.interfaces.payment.dto.SearchPointResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@PostMapping("/charge")
	public ResponseEntity<ChargeResponse> chargePoint(@RequestBody ChargeRequest chargeRequest) {

		ChargeResponse response = ChargeResponse.builder().message("충전 성공").balance(30000).build();

		return ResponseEntity.ok(response);
	}

	@PostMapping("/search")
	public ResponseEntity<SearchPointResponse> searchPoint(@RequestBody SearchPointRequest searchPointRequest) {

		SearchPointResponse response = SearchPointResponse.builder().balance(15000).build();

		return ResponseEntity.ok(response);
	}

	@PostMapping("/concert")
	public ResponseEntity<PaymentConcertResponse> paymentConcert(
		@RequestBody PaymentConcertRequest paymentConcertRequest) {

		PaymentConcertResponse response = PaymentConcertResponse.builder()
			.paymentId(1L)
			.message("결제 성공(좌석 확정)")
			.build();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<PaymentHistoryResponse> getPaymentHistory(@PathVariable("paymentId") Long paymentId) {

		PaymentHistoryResponse response = PaymentHistoryResponse.builder()
			.paymentId(paymentId)
			.paidPrice(30000)
			.concertName("콘서트1")
			.concertSeat(13)
			.concertDate("20250131")
			.build();

		return ResponseEntity.ok(response);
	}
}
