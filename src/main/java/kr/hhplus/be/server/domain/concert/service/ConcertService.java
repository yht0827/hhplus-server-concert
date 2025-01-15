package kr.hhplus.be.server.domain.concert.service;

import kr.hhplus.be.server.interfaces.concert.dto.AvailableDateResponseList;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {

    @Transactional(readOnly = true)
    public List<AvailableDateResponseList> getAvailableDates() {


        return null;
    }

    @Transactional(readOnly = true)
    public ConcertSeatResponse getAvailableSeatList(final String date) {

        return null;
    }
}
