package kr.hhplus.be.server.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.service.TokenService;
import kr.hhplus.be.server.domain.token.util.TokenUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

	private final TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// x-token 헤더 토큰 ID 조회
		String tokenId = request.getHeader("x-token");

		if (tokenId == null) {
			throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
		}

		// 토큰 ID 복호화
		String decryptTokenId = TokenUtil.decrypt(tokenId);

		// 토큰 DB 조회 여부 체크
		Token token = tokenService.checkValidToken(Long.valueOf(decryptTokenId));

		if (token == null) {
			throw new CustomException(ErrorCode.INVALID_TOKEN);
		}

		return true;
	}
}
