package kr.hhplus.be.server.application.payment.port.out;

import kr.hhplus.be.server.domain.point.entity.PointHistory;

public record ChargeInfo(Integer amount, String message, PointHistory.PointTypeEnum type) {

	public static ChargeInfo toDto(final PointHistory pointHistory) {
		return new ChargeInfo(pointHistory.getAmount(), pointHistory.getComment(), pointHistory.getType());
	}
}
