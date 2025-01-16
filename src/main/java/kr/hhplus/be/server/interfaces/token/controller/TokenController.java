package kr.hhplus.be.server.interfaces.token.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.application.TokenFacade;
import kr.hhplus.be.server.domain.token.util.TokenUtil;
import kr.hhplus.be.server.interfaces.token.dto.TokenRequest;
import kr.hhplus.be.server.interfaces.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

	private final TokenFacade tokenFacade;

	@Operation(summary = "대기열 토큰 발급", description = "유저의 대기열 토큰을 발급합니다.", tags = {"Token API"})
	@PostMapping("/create")
	public ResponseEntity<TokenResponse> create(@RequestBody final TokenRequest tokenRequest) {
		TokenResponse response = TokenResponse.toDto(tokenFacade.createToken(tokenRequest));

		// x-token 헤더에 tokenId 추가
		HttpHeaders headers = new HttpHeaders();

		// 토큰 암호화
		String encryptTokenId = TokenUtil.encrypt(String.valueOf(response.tokenId()));
		headers.add("x-token", encryptTokenId);

		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(response);
	}

}
