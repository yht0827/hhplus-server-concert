package kr.hhplus.be.server.infrastructure.concert;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;

public interface ConcertCustomRepository {

	Page<ConcertResponse.ConcertDateResponse> getAvailableConcertList(final Pageable pageable);

	Page<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date,
		final Pageable pageable);

	Long decreaseConcertSeatNumber(final Long concertId);
}
