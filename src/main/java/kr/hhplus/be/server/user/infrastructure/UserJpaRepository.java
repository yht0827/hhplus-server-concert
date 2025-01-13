package kr.hhplus.be.server.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.user.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
