package kr.hhplus.be.server.domain.reservation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.server.support.entity.BaseEntity;
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

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "reservation_status")
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;

	@Column(name = "expired_at")
	private LocalDateTime expiredAt;

	public enum ReservationStatus {
		WAIT, RESERVED, CANCELLED
	}

	@Builder
	public Reservation(Long reservationId, Long userId, ReservationStatus reservationStatus,
		LocalDateTime expiredAt) {
		this.reservationId = reservationId;
		this.userId = userId;
		this.reservationStatus = reservationStatus;
		this.expiredAt = expiredAt;
	}

	public void updateReservedStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

}
