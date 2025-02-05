package kr.hhplus.be.server.domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.instancio.Select.*;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.hhplus.be.server.domain.user.entity.User;

@DisplayName("유저 단위 테스트")
public class UserTest {

	@Test
	@DisplayName("유저 객체 생성")
	void createUserTest() {
		// given
		String userName = "홍길동";

		// when
		User user = Instancio.of(User.class)
			.set(field(User::getUserName), userName)
			.create();

		// then
		assertThat(user.getUserName()).isEqualTo(userName);
	}
}
