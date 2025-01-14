package kr.hhplus.be.server.point.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.point.domain.entity.Point;

public interface PointJpaRepository extends JpaRepository<Point, Long> {
}
