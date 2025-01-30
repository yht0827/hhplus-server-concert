package kr.hhplus.be.server.interfaces.concert.port.out;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AvailableSeatResponseList(
	@Schema(description = "예약 가능한 좌석 리스트")
	List<ConcertResponse.ConcertSeatResponse> concertSeatResponseList,

	@Schema(description = "리스트 개수", example = "1")
	Integer size
) {
	public static AvailableSeatResponseList toDto(List<ConcertResponse.ConcertSeatResponse> concertSeatResponseList) {
		return AvailableSeatResponseList
			.builder()
			.concertSeatResponseList(concertSeatResponseList)
			.size(concertSeatResponseList.size())
			.build();
	}

}
