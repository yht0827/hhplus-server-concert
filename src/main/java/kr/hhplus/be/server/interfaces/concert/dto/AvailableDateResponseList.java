package kr.hhplus.be.server.interfaces.concert.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AvailableDateResponseList(List<ConcertResponse.ConcertDateResponse> concertDateResponseList, Integer size) {

    public static AvailableDateResponseList toDto(List<ConcertResponse.ConcertDateResponse> concertDateResponseList) {
        return AvailableDateResponseList
                .builder()
                .concertDateResponseList(concertDateResponseList)
                .size(concertDateResponseList.size())
                .build();
    }

}
