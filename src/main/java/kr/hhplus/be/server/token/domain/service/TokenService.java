package kr.hhplus.be.server.token.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.token.domain.entity.Token;
import kr.hhplus.be.server.token.infrastructure.TokenRepository;
import kr.hhplus.be.server.token.interfaces.dto.TokenRequest;
import kr.hhplus.be.server.token.interfaces.dto.TokenResponse;
import kr.hhplus.be.server.user.domain.User;
import kr.hhplus.be.server.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;

	@Transactional
	public TokenResponse createToken(final TokenRequest tokenRequest) {
		User user = userRepository.findById(tokenRequest.userId());

		Token token = tokenRepository.save(Token.createToken(user));

		return TokenResponse.toDto(token);
	}
}
