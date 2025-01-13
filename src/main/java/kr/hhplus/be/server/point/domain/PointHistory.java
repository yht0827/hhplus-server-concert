package kr.hhplus.be.server.point.domain;

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

@Entity
@Getter
@Table(name = "point_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistory extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "point_history_id")
	private Long pointHistoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "point_id")
	private Point point;

	@Column
	private String type;

	@Column
	private Integer amount;

	@Builder
	public PointHistory(Long pointHistoryId, Point point, String type, Integer amount) {
		this.pointHistoryId = pointHistoryId;
		this.point = point;
		this.type = type;
		this.amount = amount;
	}
}
