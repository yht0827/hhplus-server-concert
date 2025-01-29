package kr.hhplus.be.server.interfaces.point.port.in;

import kr.hhplus.be.server.application.payment.port.in.ChargePointRequest;
import kr.hhplus.be.server.domain.point.entity.Point;
import lombok.Builder;

@Builder
public record ChargeRequest(Long userId, Integer point) {

	public ChargePointRequest toDto() {
		return new ChargePointRequest(userId, point);
	}

}
