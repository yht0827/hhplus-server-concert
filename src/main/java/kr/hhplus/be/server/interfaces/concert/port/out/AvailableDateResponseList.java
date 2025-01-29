package kr.hhplus.be.server.interfaces.concert.port.out;

import java.util.List;

import lombok.Builder;

@Builder
public record AvailableDateResponseList(List<ConcertResponse.ConcertDateResponse> concertDateResponseList,
										Integer size) {

	public static AvailableDateResponseList toDto(List<ConcertResponse.ConcertDateResponse> concertDateResponseList) {
		return AvailableDateResponseList
			.builder()
			.concertDateResponseList(concertDateResponseList)
			.size(concertDateResponseList.size())
			.build();
	}

}
