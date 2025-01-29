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

	@Column(name = "schedule_date")
	private String scheduleDate;

	@Column(name = "concert_start_time")
	private String concertStartTime;

	@Column(name = "concert_end_time")
	private String concertEndTime;

	@Builder
	public ConcertSchedule(Long concertScheduleId, String scheduleDate, String concertStartTime,
		String concertEndTime) {
		this.concertScheduleId = concertScheduleId;
		this.scheduleDate = scheduleDate;
		this.concertStartTime = concertStartTime;
		this.concertEndTime = concertEndTime;
	}
}
