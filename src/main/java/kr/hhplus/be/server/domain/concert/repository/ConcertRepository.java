package kr.hhplus.be.server.domain.concert.repository;

import kr.hhplus.be.server.interfaces.concert.dto.ConcertResponse;

import java.util.List;

public interface ConcertRepository {

    List<ConcertResponse.ConcertDateResponse> getAvailableConcertList();

    List<ConcertResponse.ConcertSeatResponse> getAvailableDateConcertSeatList(final String date);

    List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids);
}
