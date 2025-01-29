package kr.hhplus.be.server.interfaces.point.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.payment.facade.PaymentFacade;
import kr.hhplus.be.server.application.payment.port.out.PointChargeResponse;
import kr.hhplus.be.server.interfaces.point.port.in.ChargeRequest;
import kr.hhplus.be.server.interfaces.point.port.out.ChargeResponse;
import kr.hhplus.be.server.interfaces.point.port.out.SearchPointResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

	private final PaymentFacade paymentFacade;

	@GetMapping("/{pointId}")
	public ResponseEntity<SearchPointResponse> getPoint(@PathVariable final Long pointId) {
		SearchPointResponse response = SearchPointResponse.toDto(paymentFacade.getPoint(pointId));

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ChargeResponse> chargePoint(@RequestBody final ChargeRequest chargeRequest) {

		PointChargeResponse pointChargeResponse = paymentFacade.chargePoint(chargeRequest.toDto());
		ChargeResponse response = ChargeResponse.toDto(pointChargeResponse);

		return ResponseEntity.ok(response);
	}

}
