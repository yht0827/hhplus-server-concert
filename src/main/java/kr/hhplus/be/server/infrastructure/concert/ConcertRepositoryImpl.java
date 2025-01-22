package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.domain.concert.entity.Concert;
import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.concert.repository.ConcertRepository;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

	private final ConcertJpaRepository concertJpaRepository;
	private final ConcertScheduleJpaRepository concertScheduleJpaRepository;
	private final ConcertSeatJpaRepository concertSeatJpaRepository;

	@Override
	public ConcertSeat save(final ConcertSeat concertSeat) {
		return concertSeatJpaRepository.save(concertSeat);
	}

	@Override
	public Optional<Concert> findConcertById(final Long concertId) {
		return concertJpaRepository.findById(concertId);
	}

	@Override
	public List<ConcertResponse.ConcertDateResponse> getAvailableConcertList() {
		return concertJpaRepository.getAvailableConcertList();
	}

	@Override
	public List<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date) {
		return concertJpaRepository.getAvailableDateConcertSeatList(date);
	}

	@Override
	public List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids) {
		return concertJpaRepository.getReservedSeatList(ids);
	}

	@Override
	public Optional<ConcertSeat> findReservedConcertSeat(final Long concertId, final Integer seatNumber) {
		return concertJpaRepository.findReservedConcertSeat(concertId, seatNumber);
	}

	@Override
	public ConcertSeat findConcertSeat(final Long concertId, final Integer seatNumber) {
		return concertJpaRepository.findConcertSeat(concertId, seatNumber);
	}

}
