package kr.hhplus.be.server.point.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.point.domain.entity.PointHistory;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistory, Long> {
}
