package kr.hhplus.be.server.concert.interfaces.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AvailableDateResponseList(List<ConcertDateResponse> concertDateResponseList, Integer size) {
}
