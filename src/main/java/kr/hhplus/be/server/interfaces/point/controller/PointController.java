package kr.hhplus.be.server.interfaces.point.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.PointFacade;
import kr.hhplus.be.server.interfaces.point.dto.ChargeRequest;
import kr.hhplus.be.server.interfaces.point.dto.ChargeResponse;
import kr.hhplus.be.server.interfaces.point.dto.SearchPointResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

	private final PointFacade pointFacade;

	@GetMapping("/{pointId}")
	public ResponseEntity<SearchPointResponse> searchPoint(@PathVariable Long pointId) {
		SearchPointResponse response = SearchPointResponse.toDto(pointFacade.searchPoint(pointId));

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ChargeResponse> chargePoint(@RequestBody ChargeRequest chargeRequest) {
		ChargeResponse response = ChargeResponse.toDto(pointFacade.chargePoint(chargeRequest));

		return ResponseEntity.ok(response);
	}

}
