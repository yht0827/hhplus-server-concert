package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.concert.repository.ConcertRepository;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

	private final ConcertJpaRepository concertJpaRepository;
	private final ConcertScheduleJpaRepository concertScheduleJpaRepository;
	private final ConcertSeatJpaRepository concertSeatJpaRepository;
	private final ConcertSeatCategoryJpaRepository concertSeatCategoryJpaRepository;

	@Override
	public ConcertSeat save(final ConcertSeat concertSeat) {
		return concertSeatJpaRepository.save(concertSeat);
	}

	@Override
	public Page<ConcertResponse.ConcertDateResponse> getAvailableConcertList(final Pageable pageable) {
		return concertJpaRepository.getAvailableConcertList(pageable);
	}

	@Override
	public Page<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date,
		final Pageable pageable) {
		return concertJpaRepository.getAvailableDateConcertSeatList(date, pageable);
	}

	@Override
	public List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids) {
		return concertSeatJpaRepository.getReservedSeatList(ids);
	}

	@Override
	public Optional<ConcertSeat> findByConcertSeatId(final Long concertSeatId) {
		return concertSeatJpaRepository.findById(concertSeatId);
	}

	@Override
	public Integer getConcertPrice(final Long concertSeatId) {
		return concertSeatJpaRepository.getConcertPrice(concertSeatId);
	}

	@Override
	public Long decreaseConcertSeatNumber(final Long concertId) {
		return concertJpaRepository.decreaseConcertSeatNumber(concertId);
	}

}
