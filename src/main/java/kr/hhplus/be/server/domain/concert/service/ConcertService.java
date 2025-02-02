package kr.hhplus.be.server.domain.concert.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.application.payment.port.in.PaymentRequest;
import kr.hhplus.be.server.application.reservation.port.in.ReserveCommand;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.concert.repository.ConcertRepository;
import kr.hhplus.be.server.interfaces.concert.port.out.ConcertResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcertService {

	private final ConcertRepository concertRepository;

	public Page<ConcertResponse.ConcertDateResponse> getAvailableDates(final Pageable pageable) {
		return concertRepository.getAvailableConcertList(pageable);
	}

	public Page<ConcertResponse.ConcertSeatResponse> getAvailableSeatList(final String date, final Pageable pageable) {
		return concertRepository.getAvailableDateConcertSeatList(date, pageable);
	}

	public List<ConcertResponse.ConcertSeatInfoResponse> getReservedSeatList(final List<Long> ids) {
		return concertRepository.getReservedSeatList(ids);
	}

	public List<Long> extractConcertIds(Page<ConcertResponse.ConcertSeatResponse> availableSeatList) {
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

	public void updateAvailableSeats(Page<ConcertResponse.ConcertSeatResponse> availableSeatList,
		Map<Long, List<Integer>> reservedSeatMap) {
		availableSeatList.forEach(concertSeat ->
			concertSeat.updateAvailableSeat(
				reservedSeatMap.getOrDefault(concertSeat.getConcertId(), Collections.emptyList())
			));
	}

	public ConcertSeat getAvailableSeat(final ReserveCommand reserveCommand) {
		ConcertSeat concertSeat = concertRepository.findByConcertSeatId(reserveCommand.concertSeatId())
			.orElseThrow(() -> new CustomException(ErrorCode.SEAT_NOT_FOUND));

		if (concertSeat.getIsOccupied())
			throw new CustomException(ErrorCode.CONCERT_SEAT_EXIST);

		concertSeat.reserved();

		return concertSeat;
	}

	public Integer getSeatPrice(final ReserveCommand reserveCommand) {
		return concertRepository.getConcertPrice(reserveCommand.concertSeatId());
	}

	public void decreaseSeatCount(final PaymentRequest paymentRequest) {
		concertRepository.decreaseConcertSeatNumber(paymentRequest.concertId());
	}
}
