package kr.hhplus.be.server.infrastructure.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.domain.reservation.entity.ReservationSeat;

public interface ReservationSeatJpaRepository
	extends JpaRepository<ReservationSeat, Long>, ReservationSeatCustomRepository {

}
