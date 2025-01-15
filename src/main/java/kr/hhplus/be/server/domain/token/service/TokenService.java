package kr.hhplus.be.server.domain.token.service;

import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.repository.TokenRepository;
import kr.hhplus.be.server.interfaces.token.dto.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    @Transactional
    public Token createToken(final TokenRequest tokenRequest) {
        Token token = Token.createToken(tokenRequest.userId());

        return tokenRepository.save(token);
    }
}
