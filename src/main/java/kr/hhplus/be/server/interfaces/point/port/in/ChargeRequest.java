package kr.hhplus.be.server.interfaces.point.port.in;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.application.payment.port.in.ChargeCommand;
import lombok.Builder;

@Builder
public record ChargeRequest(
	@Schema(description = "사용자 ID", example = "1")
	Long userId,

	@Schema(description = "충전할 포인트", example = "10000")
	Integer point
) {
	public ChargeCommand toDto() {
		return new ChargeCommand(userId, point);
	}

}
