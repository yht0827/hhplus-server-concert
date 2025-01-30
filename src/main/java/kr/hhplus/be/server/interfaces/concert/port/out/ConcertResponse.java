package kr.hhplus.be.server.interfaces.concert.port.out;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class ConcertResponse {

	@Builder
	public record ConcertDateResponse(
		@Schema(description = "콘서트 ID", example = "1")
		Long concertId,

		@Schema(description = "콘서트 이름", example = "콘서트1")
		String concertName,

		@Schema(description = "콘서트 날짜", example = "20250125")
		String scheduleDate,

		@Schema(description = "콘서트 시작 시간", example = "20:00")
		String concertStartTime,

		@Schema(description = "콘서트 종료 시간", example = "22:00")
		String concertEndTime,

		@Schema(description = "예약 가능 좌석 개수", example = "15")
		Integer seatCount
	) {
	}

	@Getter
	public static class ConcertSeatResponse {
		@Schema(description = "콘서트 ID", example = "1")
		private final Long concertId;

		@Schema(description = "콘서트 이름", example = "콘서트1")
		private final String concertName;

		@Schema(description = "콘서트 시작 시간", example = "20:00")
		private final String concertStartTime;

		@Schema(description = "콘서트 종료 시간", example = "22:00")
		private final String concertEndTime;

		@Schema(description = "사용 중인 좌석", example = "1, 2, 3, 4, 10")
		private List<Integer> usedSeats;

		@Schema(description = "좌석 개수", example = "35")
		private final Integer availableSeatCount;

		@Builder
		public ConcertSeatResponse(Long concertId, String concertName, String concertStartTime, String concertEndTime,
			List<Integer> usedSeats, Integer availableSeatCount) {
			this.concertId = concertId;
			this.concertName = concertName;
			this.concertStartTime = concertStartTime;
			this.concertEndTime = concertEndTime;
			this.usedSeats = usedSeats;
			this.availableSeatCount = availableSeatCount;
		}

		public void updateAvailableSeat(List<Integer> usedSeats) {
			this.usedSeats = usedSeats;
		}
	}

	@Builder
	public record ConcertSeatInfoResponse(
		@Schema(description = "좌석 번호", example = "1")
		Integer seatNumber,

		@Schema(description = "콘서트 ID", example = "1")
		Long concertId
	) {
	}
}
