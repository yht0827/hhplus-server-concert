package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;
import java.util.Optional;

import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertResponse;

public interface ConcertCustomRepository {

	List<ConcertResponse.ConcertDateResponse> getAvailableConcertList();

	List<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date);

	List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids);

	Optional<ConcertSeat> findReservedConcertSeat(final Long concertId, final Integer seatNumber);

	ConcertSeat findConcertSeat(final Long concertId, final Integer seatNumber);
}
