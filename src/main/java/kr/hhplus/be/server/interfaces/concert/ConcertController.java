package kr.hhplus.be.server.interfaces.concert;

import kr.hhplus.be.server.domain.concert.service.ConcertService;
import kr.hhplus.be.server.interfaces.concert.dto.AvailableDateResponseList;
import kr.hhplus.be.server.interfaces.concert.dto.AvailableSeatResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/availableDate")
    public ResponseEntity<AvailableDateResponseList> getAvailableDateList() {


        return ResponseEntity.ok(null);
    }

    @GetMapping("/availableSeat")
    public ResponseEntity<AvailableSeatResponseList> getAvailableSeatList(@RequestParam() String date) {

        return ResponseEntity.ok(null);
    }


}
