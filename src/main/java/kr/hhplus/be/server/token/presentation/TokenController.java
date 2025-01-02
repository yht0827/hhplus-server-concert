package kr.hhplus.be.server.token.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.token.presentation.dto.TokenRequest;
import kr.hhplus.be.server.token.presentation.dto.TokenResponse;

@RestController
@RequestMapping("/token")
public class TokenController {

	@PostMapping("/create")
	public ResponseEntity<TokenResponse> create(@RequestBody TokenRequest tokenRequest) {

		TokenResponse response = TokenResponse.builder()
			.message("대기열 토큰 발급 성공")
			.tokenId(1L)
			.build();

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
