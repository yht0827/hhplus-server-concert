package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;
import java.util.Optional;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;

public interface ConcertSeatCustomRepository {

	Integer getConcertPrice(final Long concertSeatId);

	Optional<ConcertSeat> findReservedConcertSeat(final Long concertId, final Integer seatNumber);

	ConcertSeat findConcertSeat(final Long concertId, final Integer seatNumber);

	List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids);
}
