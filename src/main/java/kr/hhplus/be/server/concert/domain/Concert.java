package kr.hhplus.be.server.concert.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "concert_schedule_id")
	private ConcertSchedule concertSchedule;

	@Column(name = "concert_name")
	private String concertName;

	@Column(name = "seat_count")
	private Integer seatCount;

	@Builder
	public Concert(Long concertId, ConcertSchedule concertSchedule, String concertName, Integer seatCount) {
		this.concertId = concertId;
		this.concertSchedule = concertSchedule;
		this.concertName = concertName;
		this.seatCount = seatCount;
	}
}
