package kr.hhplus.be.server.domain.reservation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.application.reservation.port.in.ReserveSeatRequest;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;
import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.reservation.entity.ReservationSeat;
import kr.hhplus.be.server.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;

	public Reservation reserve(final ReserveSeatRequest reserveSeatRequest) {
		boolean isPresent = reservationRepository.findByConcertSeatId(reserveSeatRequest.concertSeatId()).isPresent();

		if (isPresent) {
			throw new CustomException(ErrorCode.CONCERT_SEAT_EXIST);
		}

		return reservationRepository.save(reserveSeatRequest.toReservationEntity());
	}

	public Reservation updateStatus(final Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

		if (reservation.getExpiredAt().isAfter(LocalDateTime.now())) {
			throw new CustomException(ErrorCode.RESERVATION_TIME_OUT);
		}

		if (reservation.getReservationStatus().equals(Reservation.ReservationStatus.RESERVED)) {
			throw new CustomException(ErrorCode.PAYMENT_FINISHED);
		}

		reservation.updateReservedStatus(Reservation.ReservationStatus.RESERVED);

		return reservation;
	}

	public void createReservationSeat(final Long reservationId, final Long concertSeatId) {
		ReservationSeat reservationSeat = ReservationSeat.toEntity(reservationId, concertSeatId);

		reservationRepository.save(reservationSeat);
	}

	@Transactional
	public Long updateExpiredReservation() {
		List<Long> ids = reservationRepository.findAllByExpiredReservation()
			.stream()
			.map(Reservation::getReservationId)
			.toList();

		return reservationRepository.updateCancelReservation(ids);
	}
}
