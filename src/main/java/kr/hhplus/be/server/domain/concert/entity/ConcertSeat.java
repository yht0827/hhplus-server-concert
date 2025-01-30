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

	@Column(name = "concert_seat_category_id")
	private Long concertSeatCategoryId;

	@Column(name = "is_occupied")
	private Boolean isOccupied;

	@Builder
	public ConcertSeat(Long concertSeatId, Integer seatNumber, Long concertId, Long concertSeatCategoryId,
		Boolean isOccupied) {
		this.concertSeatId = concertSeatId;
		this.seatNumber = seatNumber;
		this.concertId = concertId;
		this.concertSeatCategoryId = concertSeatCategoryId;
		this.isOccupied = isOccupied;
	}

	public void reserved() {
		this.isOccupied = true;
	}

}
