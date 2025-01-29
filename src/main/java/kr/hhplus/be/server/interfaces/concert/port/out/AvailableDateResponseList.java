package kr.hhplus.be.server.interfaces.concert.port.out;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AvailableDateResponseList(
	@Schema(description = "예약 가능한 날짜 리스트")
	List<ConcertResponse.ConcertDateResponse> concertDateResponseList,

	@Schema(description = "리스트 개수", example = "2")
	Integer size
) {
	public static AvailableDateResponseList toDto(List<ConcertResponse.ConcertDateResponse> concertDateResponseList) {
		return AvailableDateResponseList
			.builder()
			.concertDateResponseList(concertDateResponseList)
			.size(concertDateResponseList.size())
			.build();
	}

}
