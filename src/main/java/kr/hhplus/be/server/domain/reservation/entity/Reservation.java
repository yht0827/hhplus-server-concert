package kr.hhplus.be.server.domain.reservation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.server.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long reservationId;

	@Column(name = "concert_seat_id")
	private Long concertSeatId;

	@Column(name = "user_id")
	private Long userId;

	@Column
	private ReservationStatus status;

	@Column(name = "expired_at")
	private LocalDateTime expiredAt;

	public enum ReservationStatus {
		WAIT, RESERVED
	}

	@Builder
	public Reservation(Long reservationId, Long concertSeatId, Long userId, ReservationStatus status,
		LocalDateTime expiredAt) {
		this.reservationId = reservationId;
		this.concertSeatId = concertSeatId;
		this.userId = userId;
		this.status = status;
		this.expiredAt = expiredAt;
	}

	public void updateReservedStatus() {
		this.status = ReservationStatus.RESERVED;
	}

}
