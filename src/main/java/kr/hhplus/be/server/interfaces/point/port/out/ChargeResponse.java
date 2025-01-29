package kr.hhplus.be.server.interfaces.point.port.out;

import kr.hhplus.be.server.application.payment.port.out.PointChargeResponse;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import lombok.Builder;

@Builder
public record ChargeResponse(Integer balance, String message, PointHistory.PointTypeEnum type) {

	public static ChargeResponse toDto(final PointChargeResponse pointChargeResponse) {
		return ChargeResponse.builder()
			.balance(pointChargeResponse.balance())
			.message(pointChargeResponse.message())
			.type(pointChargeResponse.type())
			.build();
	}
}
