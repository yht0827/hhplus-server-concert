package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;

import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;

public interface ConcertCustomRepository {

	List<ConcertResponse.ConcertDateResponse> getAvailableConcertList();

	List<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date);

	Long decreaseConcertSeatNumber(final Long concertId);
}
