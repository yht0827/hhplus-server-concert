package kr.hhplus.be.server.infrastructure.point;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.domain.point.entity.PointHistory;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistory, Long> {
}
