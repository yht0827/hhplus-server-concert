package kr.hhplus.be.server.interfaces.concert.port.out;

import java.util.List;

import lombok.Builder;

@Builder
public record AvailableSeatResponseList(List<ConcertResponse.ConcertSeatResponse> concertSeatResponseList,
										Integer size) {

	public static AvailableSeatResponseList toDto(List<ConcertResponse.ConcertSeatResponse> concertSeatResponseList) {
		return AvailableSeatResponseList
			.builder()
			.concertSeatResponseList(concertSeatResponseList)
			.size(concertSeatResponseList.size())
			.build();
	}

}
