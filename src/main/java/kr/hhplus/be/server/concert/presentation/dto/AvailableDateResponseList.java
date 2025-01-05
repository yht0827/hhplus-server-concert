package kr.hhplus.be.server.concert.presentation.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record AvailableDateResponseList(List<ConcertResponse> avaliableList, Integer size) {
}
