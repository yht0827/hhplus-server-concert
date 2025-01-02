package kr.hhplus.be.server.concert.presentation.dto;

import lombok.Builder;

@Builder
public record ConcertResponse(Long concertId, String concertName, String concertDate, Integer availableSeatCount) {
}