package kr.hhplus.be.server.domain.concert.repository;

import java.util.List;
import java.util.Optional;

import kr.hhplus.be.server.domain.concert.entity.Concert;
import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertResponse;

public interface ConcertRepository {

	List<ConcertResponse.ConcertDateResponse> getAvailableConcertList();

	List<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date);

	List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids);

    Optional<ConcertSeat> findReservedConcertSeat(final Long concertId, final Integer seatNumber);

    ConcertSeat findConcertSeat(final Long concertId, final Integer seatNumber);

    ConcertSeat save(final ConcertSeat concertSeat);

	Optional<Concert> findConcertById(final Long concertId);

}
