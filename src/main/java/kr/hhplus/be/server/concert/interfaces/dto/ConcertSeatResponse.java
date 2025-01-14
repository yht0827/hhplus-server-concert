package kr.hhplus.be.server.concert.interfaces.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ConcertSeatResponse(Long concertId, String concertName, String concertTime, List<Integer> availableSeats,
                                  Integer availableSeatCount) {
}
