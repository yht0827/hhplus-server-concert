package kr.hhplus.be.server.payment.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.payment.presentation.dto.ChargeRequest;
import kr.hhplus.be.server.payment.presentation.dto.ChargeResponse;
import kr.hhplus.be.server.payment.presentation.dto.SearchPointRequest;
import kr.hhplus.be.server.payment.presentation.dto.SearchPointResponse;

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

}
