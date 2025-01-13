package kr.hhplus.be.server.user.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.user.domain.User;
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
