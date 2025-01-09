package kr.hhplus.be.server.point.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.point.domain.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}
