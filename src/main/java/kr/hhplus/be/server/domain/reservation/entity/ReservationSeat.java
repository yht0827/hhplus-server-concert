package kr.hhplus.be.server.domain.reservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation_seat")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_seat_id")
	private Long reservationSeatId;

	@Column(name = "reservation_id")
	private Long reservationId;

	@Column(name = "concert_seat_id")
	private Long concertSeatId;

	@Builder
	public ReservationSeat(Long reservationSeatId, Long reservationId, Long concertSeatId) {
		this.reservationSeatId = reservationSeatId;
		this.reservationId = reservationId;
		this.concertSeatId = concertSeatId;
	}

	public static ReservationSeat toEntity(final Long reservationId, final Long concertSeatId) {
		return ReservationSeat.builder()
			.reservationId(reservationId)
			.concertSeatId(concertSeatId)
			.build();
	}

}
