package kr.hhplus.be.server.interfaces.point.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.payment.facade.PaymentFacade;
import kr.hhplus.be.server.application.payment.port.out.ChargeInfo;
import kr.hhplus.be.server.support.annnotation.AuthorizationToken;
import kr.hhplus.be.server.interfaces.point.port.in.ChargeRequest;
import kr.hhplus.be.server.interfaces.point.port.out.ChargeResponse;
import kr.hhplus.be.server.interfaces.point.port.out.PointResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController implements PointAPI {

	private final PaymentFacade paymentFacade;

	@GetMapping("/{pointId}")
	@AuthorizationToken
	public ResponseEntity<PointResponse> getPoint(@PathVariable final Long pointId) {
		PointResponse response = PointResponse.toDto(paymentFacade.getPoint(pointId));

		return ResponseEntity.ok(response);
	}

	@PostMapping
	@AuthorizationToken
	public ResponseEntity<ChargeResponse> chargePoint(@RequestBody final ChargeRequest chargeRequest) {

		ChargeInfo chargeInfo = paymentFacade.chargePoint(chargeRequest.toDto());
		ChargeResponse response = ChargeResponse.toDto(chargeInfo);

		return ResponseEntity.ok(response);
	}

}
