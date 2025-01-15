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
@Table(name = "concert_schedule")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertSchedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "concert_schedule_id")
	private Long concertScheduleId;

	@Column(name = "concert_date")
	private String concertDate;

	@Column(name = "concert_time")
	private String concertTime;

	@Builder
	public ConcertSchedule(Long concertScheduleId, String concertDate, String concertTime) {
		this.concertScheduleId = concertScheduleId;
		this.concertDate = concertDate;
		this.concertTime = concertTime;
	}
}
