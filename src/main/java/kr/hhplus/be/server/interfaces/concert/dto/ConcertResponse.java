package kr.hhplus.be.server.interfaces.concert.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ConcertResponse {

    @Builder
    public record ConcertDateResponse(Long concertId, String concertName, String concertDate, String concertTime,
                                      Integer seatCount) {
    }

    @Getter
    public static class ConcertSeatResponse {

        private final Long concertId;
        private final String concertName;
        private final String concertTime;
        private List<Integer> usedSeats;
        private final Integer availableSeatCount;

        @Builder
        public ConcertSeatResponse(Long concertId, String concertName, String concertTime, List<Integer> usedSeats, Integer availableSeatCount) {
            this.concertId = concertId;
            this.concertName = concertName;
            this.concertTime = concertTime;
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
