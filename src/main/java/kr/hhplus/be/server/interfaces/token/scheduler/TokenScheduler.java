package kr.hhplus.be.server.interfaces.token.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.domain.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenScheduler {

	private final TokenService tokenService;

	@Scheduled(fixedRate = 20000)
	public void processActiveTokens() {
		Long size = tokenService.updateActiveToken();
		log.info("Wait -> Active 된 토큰 개수: {}", size);
	}
}
