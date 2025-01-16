package kr.hhplus.be.server.domain.point.repository;

import java.util.Optional;

import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.entity.PointHistory;

public interface PointRepository {
	Point savePoint(final Point point);

	PointHistory savePointHistory(final PointHistory pointHistory);

	Optional<Point> findPointById(final Long id);

	Optional<Point> findPointByUserId(final Long userId);
}
