package kr.hhplus.be.server.token.interfaces.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.token.domain.service.TokenService;
import kr.hhplus.be.server.token.interfaces.dto.TokenRequest;
import kr.hhplus.be.server.token.interfaces.dto.TokenResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

	private final TokenService tokenService;

	@Operation(summary = "대기열 토큰 발급", description = "유저의 대기열 토큰을 발급합니다.", tags = {"Token API"})
	@PostMapping("/create")
	public ResponseEntity<TokenResponse> create(@RequestBody TokenRequest tokenRequest) {
		TokenResponse response = tokenService.createToken(tokenRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
