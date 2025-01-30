package kr.hhplus.be.server.infrastructure.reservation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.domain.reservation.entity.Reservation;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long>, ReservationJpaCustomRepository {
}
