package kr.hhplus.be.server.domain.concert.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;

public interface ConcertRepository {

	ConcertSeat save(final ConcertSeat concertSeat);

	Page<ConcertResponse.ConcertDateResponse> getAvailableConcertList(final Pageable pageable);

	Page<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date,
		final Pageable pageable);

	List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids);

	Optional<ConcertSeat> findByConcertSeatId(final Long concertSeatId);

	Integer getConcertPrice(final Long concertSeatId);

	Long decreaseConcertSeatNumber(final Long concertId);
}
