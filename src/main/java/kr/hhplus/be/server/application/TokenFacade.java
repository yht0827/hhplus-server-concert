package kr.hhplus.be.server.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.interfaces.token.dto.TokenRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenFacade {

	private final TokenService tokenService;

	@Transactional
	public Token createToken(final TokenRequest tokenRequest) {
		return tokenService.createToken(tokenRequest);
	}

}
