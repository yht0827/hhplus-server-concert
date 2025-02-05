package kr.hhplus.be.server.interfaces.token.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.application.token.facade.TokenFacade;
import kr.hhplus.be.server.application.token.port.out.TokenInfo;
import kr.hhplus.be.server.interfaces.token.port.in.TokenRequest;
import kr.hhplus.be.server.interfaces.token.port.out.TokenResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController implements TokenAPI {

	private final TokenFacade tokenFacade;

	@PostMapping("/create")
	public ResponseEntity<TokenResponse> create(@RequestBody final TokenRequest tokenRequest) {
		TokenInfo tokenInfo = tokenFacade.createToken(tokenRequest.toDto());
		TokenResponse response = TokenResponse.toDto(tokenInfo);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
