package kr.hhplus.be.server.user.infrastructure;

import java.util.Optional;

import kr.hhplus.be.server.user.domain.User;

public interface UserRepository {

	Optional<User> findById(final Long id);
}
