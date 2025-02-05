package kr.hhplus.be.server.support.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;
import kr.hhplus.be.server.support.util.TokenUtil;
import kr.hhplus.be.server.domain.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationTokenAop {

	private final TokenService tokenService;

	@Pointcut("@annotation(kr.hhplus.be.server.support.annnotation.AuthorizationToken)")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void Authorization(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		String tokenId = request.getHeader("x-token");

		if (StringUtils.isEmpty(tokenId)) {
			throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
		}

		// 토큰 ID 복호화
		String decryptTokenId = TokenUtil.decrypt(tokenId);

		// 토큰 DB 조회 여부 체크
		tokenService.checkValidToken(Long.valueOf(decryptTokenId));
	}

}
