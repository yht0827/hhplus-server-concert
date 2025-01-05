package kr.hhplus.be.server.concert.presentation.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record ConcertSeatResponse(Long concertId, String concertName, List<Integer> availableSeats,
								  Integer availableSeatCount) {
}
