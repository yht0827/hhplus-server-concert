package kr.hhplus.be.server.application.token.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.token.port.in.TokenCommand;
import kr.hhplus.be.server.application.token.port.out.TokenInfo;
import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.support.util.TokenUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenFacade {

	private final TokenService tokenService;

	@Transactional
	public TokenInfo createToken(final TokenCommand tokenCommand) {
		Token token = tokenService.createToken(tokenCommand);

		String encryptTokenId = TokenUtil.encrypt(String.valueOf(token.getTokenId()));

		return TokenInfo.toDto(token, encryptTokenId);
	}
}
