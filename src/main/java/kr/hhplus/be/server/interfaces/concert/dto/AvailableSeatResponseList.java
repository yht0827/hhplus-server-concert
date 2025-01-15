package kr.hhplus.be.server.interfaces.concert.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AvailableSeatResponseList(List<ConcertSeatResponse> concertSeatResponseList, Integer size) {
}
