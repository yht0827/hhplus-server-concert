package kr.hhplus.be.server.infrastructure.user;

import java.util.Optional;

import kr.hhplus.be.server.domain.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public Optional<User> findById(Long id) {
		return userJpaRepository.findById(id);

	}
}
