package kr.hhplus.be.server.domain.concert.entity;

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
@Table(name = "concert_seat")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertSeat extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "concert_seat_id")
	private Long concertSeatId;

	@Column(name = "seat_number")
	private Integer seatNumber;

	@Column(name = "concert_id")
	private Long concertId;

	@Builder
	public ConcertSeat(Long concertSeatId, Integer seatNumber, Long concertId) {
		this.concertSeatId = concertSeatId;
		this.seatNumber = seatNumber;
		this.concertId = concertId;
	}

	public void updateConcertSeat(final Long concertId) {
		this.concertId = concertId;
	}
}
