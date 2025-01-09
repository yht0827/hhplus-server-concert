package kr.hhplus.be.server.point.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.point.domain.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
}
