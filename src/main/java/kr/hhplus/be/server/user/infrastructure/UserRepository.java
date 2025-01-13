package kr.hhplus.be.server.user.infrastructure;

import kr.hhplus.be.server.user.domain.User;

public interface UserRepository {

	User findById(final Long id);
}
