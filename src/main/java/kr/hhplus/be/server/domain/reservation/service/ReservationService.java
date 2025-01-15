package kr.hhplus.be.server.domain.reservation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;
import kr.hhplus.be.server.domain.reservation.repository.ReservationRepository;
import kr.hhplus.be.server.interfaces.reservation.dto.ReserveRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;

	@Transactional
	public Reservation reserve(final ReserveRequest reserveRequest) {
		Reservation reservation = reserveRequest.toEntity();

		return reservationRepository.save(reservation);
	}

}
