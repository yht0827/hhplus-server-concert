package kr.hhplus.be.server.infrastructure.point;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import kr.hhplus.be.server.domain.point.entity.Point;

public interface PointJpaRepository extends JpaRepository<Point, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from Point p where p.userId = :userId")
	Optional<Point> findByUserIdWithLock(Long userId);
}
