package kr.hhplus.be.server.application.token.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.token.port.in.SignTokenRequest;
import kr.hhplus.be.server.application.token.port.out.SignTokenResponse;
import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.support.util.TokenUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenFacade {

	private final TokenService tokenService;

	@Transactional
	public SignTokenResponse createToken(final SignTokenRequest signTokenRequest) {
		Token token = tokenService.createToken(signTokenRequest);

		String encryptTokenId = TokenUtil.encrypt(String.valueOf(token.getTokenId()));

		return SignTokenResponse.toDto(token, encryptTokenId);
	}

}
