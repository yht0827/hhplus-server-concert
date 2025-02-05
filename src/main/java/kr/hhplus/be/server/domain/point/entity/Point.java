package kr.hhplus.be.server.domain.point.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Getter
@Table(name = "point")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "point_id")
	private Long pointId;

	@Column(name = "user_id")
	private Long userId;

	@Column
	private Integer balance;

	@Builder
	public Point(Long pointId, Long userId, Integer balance) {
		this.pointId = pointId;
		this.userId = userId;
		this.balance = balance;
	}

	public void chargePoint(Integer point) {
		this.balance += point;
	}

	public void usePoint(Integer point) {
		this.balance -= point;
	}
}
