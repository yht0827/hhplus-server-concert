package kr.hhplus.be.server.application.token;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.application.token.port.in.TokenCommand;
import kr.hhplus.be.server.application.token.port.out.TokenInfo;
import kr.hhplus.be.server.support.BaseIntegrationTest;
import kr.hhplus.be.server.support.util.TokenUtil;

@DisplayName("토큰 Facade 테스트")
public class TokenFacadeTest extends BaseIntegrationTest {

	@Test
	@DisplayName("redis 대기열 토큰 발급 테스트")
	void createTokenTest() {
		// given
		Long userId = 1L;
		TokenCommand tokenCommand = new TokenCommand(userId);

		// when
		TokenInfo tokenInfo = tokenFacade.createToken(tokenCommand);

		// then
		String decryptToken = TokenUtil.decrypt(tokenInfo.userId());
		assertThat(decryptToken).isEqualTo(String.valueOf(userId));
	}
}
