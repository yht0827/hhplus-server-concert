package kr.hhplus.be.server.concert.interfaces.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AvailableSeatResponseList(List<ConcertSeatResponse> concertSeatResponseList, Integer size) {
}
