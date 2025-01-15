package kr.hhplus.be.server.interfaces.concert.dto;

import lombok.Builder;

@Builder
public record ConcertDateResponse(Long concertId, String concertName, String concertDate, String concertTime,
                                  Integer seatCount) {
}
