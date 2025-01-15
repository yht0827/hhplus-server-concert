package kr.hhplus.be.server.interfaces.concert;

import kr.hhplus.be.server.application.ConcertFacade;
import kr.hhplus.be.server.interfaces.concert.dto.AvailableDateResponseList;
import kr.hhplus.be.server.interfaces.concert.dto.AvailableSeatResponseList;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertDateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertFacade concertFacade;

    @GetMapping("/availableDate")
    public ResponseEntity<AvailableDateResponseList> getAvailableDateList() {
        AvailableDateResponseList response = AvailableDateResponseList.toDto(concertFacade.getAvailableDates());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/availableSeat")
    public ResponseEntity<AvailableSeatResponseList> getAvailableSeatList(
            @ModelAttribute ConcertDateRequest concertDateRequest) {
        AvailableSeatResponseList response = AvailableSeatResponseList
                .toDto(concertFacade.getAvailableSeatList(concertDateRequest.date()));

        return ResponseEntity.ok(response);
    }
}
