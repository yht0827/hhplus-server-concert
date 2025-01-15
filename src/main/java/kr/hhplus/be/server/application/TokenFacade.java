package kr.hhplus.be.server.application;

import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.interfaces.token.dto.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;

    public Token createToken(final TokenRequest tokenRequest) {
        return tokenService.createToken(tokenRequest);
    }

}
