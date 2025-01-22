package kr.hhplus.be.server.domain.concert.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.concert.entity.Concert;
import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.concert.repository.ConcertRepository;
import kr.hhplus.be.server.interfaces.concert.dto.ConcertResponse;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcertService {

	private final ConcertRepository concertRepository;

	@Transactional(readOnly = true)
	public List<ConcertResponse.ConcertDateResponse> getAvailableDates() {
		return concertRepository.getAvailableConcertList();
	}

	@Transactional(readOnly = true)
	public List<ConcertResponse.ConcertSeatResponse> getAvailableSeatList(final String date) {
		return concertRepository.getAvailableDateConcertSeatList(date);
	}

	@Transactional(readOnly = true)
	public List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids) {
		return concertRepository.getReservedSeatList(ids);
	}

	public List<Long> extractConcertIds(List<ConcertResponse.ConcertSeatResponse> availableSeatList) {
		return availableSeatList.stream()
			.map(ConcertResponse.ConcertSeatResponse::getConcertId)
			.toList();
	}

	public Map<Long, List<Integer>> createReservedSeatMap(
		List<ConcertResponse.ConcertSeatInfoResponse> reservedSeatList) {
		return reservedSeatList.stream()
			.collect(Collectors.groupingBy(
				ConcertResponse.ConcertSeatInfoResponse::concertId,
				Collectors.mapping(ConcertResponse.ConcertSeatInfoResponse::seatNumber, Collectors.toList())
			));
	}

	public void updateAvailableSeats(List<ConcertResponse.ConcertSeatResponse> availableSeatList,
		Map<Long, List<Integer>> reservedSeatMap) {
		availableSeatList.forEach(concertSeat ->
			concertSeat.updateAvailableSeat(
				reservedSeatMap.getOrDefault(concertSeat.getConcertId(), Collections.emptyList())
			));
	}

	@Transactional(readOnly = true)
	public void findReservedConcertSeat(final ReserveRequest reserveRequest) {
		concertRepository.findReservedConcertSeat(reserveRequest.concertId(),
				reserveRequest.seatNumber())
			.ifPresent(concertSeat -> {
				throw new CustomException(ErrorCode.CONCERT_SEAT_EXIST);
			});
	}

	@Transactional
	public ConcertSeat upsertConcertSeat(final ReserveRequest reserveRequest) {

		ConcertSeat concertSeat = concertRepository.findConcertSeat(reserveRequest.concertId(),
			reserveRequest.seatNumber());

		if (concertSeat == null) {
			ConcertSeat newConcertSeat = reserveRequest.toConcertEntity();

			return concertRepository.save(newConcertSeat);
		}

		concertSeat.updateConcertSeat(concertSeat.getConcertId());

		return concertSeat;
	}

	@Transactional
	public Concert getConcert(final Long concertId) {
		return concertRepository.findConcertById(concertId)
			.orElseThrow(() -> new CustomException(ErrorCode.CONCERT_NOT_FOUND));
	}
}
