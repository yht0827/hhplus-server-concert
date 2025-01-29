package kr.hhplus.be.server.interfaces.concert.port.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

public class ConcertResponse {

	@Builder
	public record ConcertDateResponse(Long concertId, String concertName, String concertDate, String concertStartTime,
									  String concertEndTime, Integer seatCount) {
	}

	@Getter
	public static class ConcertSeatResponse {

		private final Long concertId;
		private final String concertName;
		private final String concertStartTime;
		private final String concertEndTime;
		private List<Integer> usedSeats;
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
	public record ConcertSeatInfoResponse(Integer seatNumber, Long concertId) {
	}
}
