package kr.hhplus.be.server.interfaces.point.dto;

import kr.hhplus.be.server.domain.point.entity.Point;
import lombok.Builder;

@Builder
public record ChargeRequest(Long userId, Integer point) {

	public Point toEntity() {
		return Point.builder()
			.userId(this.userId)
			.balance(this.point)
			.build();
	}

}
