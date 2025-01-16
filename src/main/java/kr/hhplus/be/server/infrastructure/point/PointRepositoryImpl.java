package kr.hhplus.be.server.infrastructure.point;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import kr.hhplus.be.server.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

	private final PointJpaRepository pointJpaRepository;
	private final PointHistoryJpaRepository pointHistoryJpaRepository;

	@Override
	public Point savePoint(final Point point) {
		return pointJpaRepository.save(point);
	}

	@Override
	public PointHistory savePointHistory(final PointHistory pointHistory) {
		return pointHistoryJpaRepository.save(pointHistory);
	}

	@Override
	public Optional<Point> findPointById(final Long id) {
		return pointJpaRepository.findById(id);
	}

	@Override
	public Optional<Point> findPointByUserId(final Long userId) {
		return pointJpaRepository.findByUserId(userId);
	}
}
