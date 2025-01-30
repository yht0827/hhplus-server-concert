package kr.hhplus.be.server.domain.concert.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.application.reservation.port.in.ReserveSeatRequest;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.concert.repository.ConcertRepository;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcertService {

	private final ConcertRepository concertRepository;

	public List<ConcertResponse.ConcertDateResponse> getAvailableDates() {
		return concertRepository.getAvailableConcertList();
	}

	public List<ConcertResponse.ConcertSeatResponse> getAvailableSeatList(final String date) {
		return concertRepository.getAvailableDateConcertSeatList(date);
	}

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

	public ConcertSeat getAvailableSeat(final ReserveSeatRequest reserveSeatRequest) {
		ConcertSeat concertSeat = concertRepository.findByConcertSeatId(reserveSeatRequest.concertSeatId())
			.orElseThrow(() -> new CustomException(ErrorCode.SEAT_NOT_FOUND));

		if (concertSeat.getIsOccupied())
			throw new CustomException(ErrorCode.CONCERT_SEAT_EXIST);

		concertSeat.reserved();

		return concertSeat;
	}

	public Integer getSeatPrice(final ReserveSeatRequest reserveSeatRequest) {
		return concertRepository.getConcertPrice(reserveSeatRequest.concertSeatId());
	}

	public void decreaseSeatCount(final PaymentRequest paymentRequest) {
		concertRepository.decreaseConcertSeatNumber(paymentRequest.concertId());
	}
}
