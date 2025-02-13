package kr.hhplus.be.server.application.token.facade;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.application.token.port.in.TokenCommand;
import kr.hhplus.be.server.application.token.port.out.TokenInfo;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.support.util.TokenUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenFacade {

	private final TokenService tokenService;

	public TokenInfo createToken(final TokenCommand tokenCommand) {
		String userId = tokenService.createWaitToken(tokenCommand);

		String encryptTokenId = TokenUtil.encrypt(String.valueOf(userId));

		return TokenInfo.toDto(encryptTokenId);
	}
}
