package kr.hhplus.be.server.interfaces.point.port.out;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.payment.port.out.ChargeInfo;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import lombok.Builder;

@Builder
public record ChargeResponse(
	@Schema(description = "충전한 포인트", example = "10000")
	Integer amount,

	@Schema(description = "메세지", example = "10000원 충전")
	String message,

	@Schema(description = "포인트 타입", example = "CHARGE")
	PointHistory.PointTypeEnum type
) {
	public static ChargeResponse toDto(final ChargeInfo chargeInfo) {
		return ChargeResponse.builder()
			.amount(chargeInfo.amount())
			.message(chargeInfo.message())
			.type(chargeInfo.type())
			.build();
	}
}
