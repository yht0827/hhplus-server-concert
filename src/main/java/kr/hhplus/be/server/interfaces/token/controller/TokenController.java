package kr.hhplus.be.server.interfaces.token.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.application.token.facade.TokenFacade;
import kr.hhplus.be.server.application.token.port.out.SignTokenResponse;
import kr.hhplus.be.server.interfaces.token.port.in.TokenRequest;
import kr.hhplus.be.server.interfaces.token.port.out.TokenResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

	private final TokenFacade tokenFacade;

	@Operation(summary = "대기열 토큰 발급", description = "유저의 대기열 토큰을 발급합니다.", tags = {"Token API"})
	@PostMapping("/create")
	public ResponseEntity<TokenResponse> create(@RequestBody final TokenRequest tokenRequest) {
		SignTokenResponse signTokenResponse = tokenFacade.createToken(tokenRequest.toDto());

		TokenResponse response = TokenResponse.toDto(signTokenResponse);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
