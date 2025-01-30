package kr.hhplus.be.server.application.payment.port.out;

import kr.hhplus.be.server.domain.point.entity.PointHistory;

public record PointChargeResponse(Integer amount, String message, PointHistory.PointTypeEnum type) {

	public static PointChargeResponse toDto(final PointHistory pointHistory) {
		return new PointChargeResponse(pointHistory.getAmount(), pointHistory.getComment(), pointHistory.getType());
	}
}
