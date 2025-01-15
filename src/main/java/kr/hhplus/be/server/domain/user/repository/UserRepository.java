package kr.hhplus.be.server.domain.user.repository;

import java.util.Optional;

import kr.hhplus.be.server.domain.user.entity.User;

public interface UserRepository {

	Optional<User> findById(final Long id);
}
