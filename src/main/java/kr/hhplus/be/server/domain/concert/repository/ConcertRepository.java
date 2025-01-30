package kr.hhplus.be.server.domain.concert.repository;

import java.util.List;
import java.util.Optional;

import kr.hhplus.be.server.domain.concert.entity.Concert;
import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;

public interface ConcertRepository {

	ConcertSeat save(final ConcertSeat concertSeat);

	Optional<Concert> findConcertById(final Long concertId);

	List<ConcertResponse.ConcertDateResponse> getAvailableConcertList();

	List<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date);

	List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids);

	Optional<ConcertSeat> findByConcertSeatId(final Long concertSeatId);

	Integer getConcertPrice(final Long concertSeatId);

	Long decreaseConcertSeatNumber(final Long concertId);
}
