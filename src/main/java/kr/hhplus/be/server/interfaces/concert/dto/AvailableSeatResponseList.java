package kr.hhplus.be.server.interfaces.concert.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AvailableSeatResponseList(List<ConcertResponse.ConcertSeatResponse> concertSeatResponseList,
                                        Integer size) {

    public static AvailableSeatResponseList toDto(List<ConcertResponse.ConcertSeatResponse> concertSeatResponseList) {
        return AvailableSeatResponseList
                .builder()
                .concertSeatResponseList(concertSeatResponseList)
                .size(concertSeatResponseList.size())
                .build();
    }

}
