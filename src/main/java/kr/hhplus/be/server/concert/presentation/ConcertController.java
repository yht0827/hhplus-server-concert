package kr.hhplus.be.server.concert.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.concert.presentation.dto.AvailableDateResponseList;
import kr.hhplus.be.server.concert.presentation.dto.ConcertResponse;
import kr.hhplus.be.server.concert.presentation.dto.ConcertSeatResponse;

@RestController
@RequestMapping("/concert")
public class ConcertController {

	@GetMapping("/availableDate")
	public ResponseEntity<AvailableDateResponseList> getAvailableDateList() {

		ConcertResponse response1 = ConcertResponse.builder()
			.concertId(1L)
			.concertName("콘서트1")
			.concertDate("20250125")
			.availableSeatCount(5)
			.build();

		ConcertResponse response2 = ConcertResponse.builder()
			.concertId(3L)
			.concertName("콘서트3")
			.concertDate("20250127")
			.availableSeatCount(15)
			.build();

		AvailableDateResponseList responseList = AvailableDateResponseList.builder()
			.avaliableList(List.of(response1, response2))
			.size(2)
			.build();

		return ResponseEntity.ok(responseList);
	}

	@GetMapping("/availableSeat")
	public ResponseEntity<ConcertSeatResponse> getSchedules(@RequestParam() String date) {

		ConcertSeatResponse response = ConcertSeatResponse.builder()
			.concertId(1L)
			.concertName("콘서트1")
			.availableSeats(List.of(1, 2, 3, 4, 5))
			.availableSeatCount(5)
			.build();

		return ResponseEntity.ok(response);
	}



}
