package kr.hhplus.be.server.infrastructure.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.support.BaseIntegrationTest;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;

public class UserRepositoryTest extends BaseIntegrationTest {

	@Test
	@DisplayName("유저를 조회한다.")
	void findUserRepositoryTest() {
		// given
		userJpaRepository.save(User.builder().userName("홍길동").build());

		// when
		User user = userRepository.findById(1L)
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

		// then
		assertThat(user.getUserName()).isEqualTo("홍길동");
	}
}
