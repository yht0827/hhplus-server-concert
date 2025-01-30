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

@Getter
@Entity
@Table(name = "concert")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Concert extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "concert_id")
	private Long concertId;

	@Column(name = "concert_schedule_id")
	private Long concertScheduleId;

	@Column(name = "concert_name")
	private String concertName;

	@Column(name = "seat_count")
	private Integer seatCount;

	@Builder
	public Concert(Long concertId, Long concertScheduleId, String concertName, Integer seatCount,
		Integer concertPrice) {
		this.concertId = concertId;
		this.concertScheduleId = concertScheduleId;
		this.concertName = concertName;
		this.seatCount = seatCount;
	}
}
