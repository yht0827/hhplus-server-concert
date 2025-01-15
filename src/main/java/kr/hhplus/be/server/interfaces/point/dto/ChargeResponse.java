package kr.hhplus.be.server.interfaces.point.dto;

import kr.hhplus.be.server.domain.point.entity.PointHistory;
import lombok.Builder;

@Builder
public record ChargeResponse(Integer balance, String message, PointHistory.PointTypeEnum type) {

	public static ChargeResponse toDto(final PointHistory pointHistory) {
		return ChargeResponse.builder()
			.balance(pointHistory.getAmount())
			.message(pointHistory.getComment())
			.type(pointHistory.getType())
			.build();
	}
}
