package kr.hhplus.be.server.domain.reservation.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.common.annnotation.DistributeLock;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.reservation.repository.ReservationRepository;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;

	@DistributeLock(key = "'reserve:' + #reserveRequest.concertSeatId()")
	public Reservation reserve(final ReserveRequest reserveRequest) {
		Reservation reservation = reserveRequest.toReservationEntity();

		Optional<Reservation> existingReservation = reservationRepository.findReservedConcertSeat(
			reservation.getConcertSeatId());

		if (existingReservation.isPresent()) {
			throw new CustomException(ErrorCode.CONCERT_SEAT_EXIST);
		}

		return reservationRepository.save(reservation);
	}

	@Transactional
	public Reservation updateStatus(final Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

		reservation.updateReservedStatus();

		return reservation;
	}

	@Transactional(readOnly = true)
	public void checkReservedStatus(final Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

		if (reservation.getStatus() == Reservation.ReservationStatus.RESERVED) {
			throw new CustomException(ErrorCode.PAYMENT_FINISHED);
		}

	}
}
