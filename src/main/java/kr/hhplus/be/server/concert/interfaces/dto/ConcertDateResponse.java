package kr.hhplus.be.server.concert.interfaces.dto;

import lombok.Builder;

@Builder
public record ConcertDateResponse(Long concertId, String concertName, String concertDate, String concertTime,
                                  Integer seatCount) {
}
