package kr.hhplus.be.server.application;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.concert.service.ConcertService;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConcertFacade {

	private final ConcertService concertService;

	@Transactional
	public List<ConcertResponse.ConcertDateResponse> getAvailableDates() {
		return concertService.getAvailableDates();
	}

	@Transactional
	public List<ConcertResponse.ConcertSeatResponse> getAvailableSeatList(final String date) {

		// 사용 가능한 좌석 리스트 조회
		List<ConcertResponse.ConcertSeatResponse> availableSeatList = concertService.getAvailableSeatList(date);

		//  콘서트 ID 리스트 추출
		List<Long> concertIds = concertService.extractConcertIds(availableSeatList);

		// 예약된 좌석 리스트 조회
		List<ConcertResponse.ConcertSeatInfoResponse> reservedSeatList = concertService.getReservedSeatList(concertIds);

		// 예약된 좌석 정보를 Map(ConcertId, 예약된 자리 Id 리스트) 변환
		Map<Long, List<Integer>> reversedSeatMap = concertService.createReservedSeatMap(reservedSeatList);

		// 사용 가능한 좌석 정보 업데이트
		concertService.updateAvailableSeats(availableSeatList, reversedSeatMap);

		return availableSeatList;
	}

}
