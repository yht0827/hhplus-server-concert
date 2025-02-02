package kr.hhplus.be.server.interfaces.point.port.out;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.domain.point.entity.Point;
import lombok.Builder;

@Builder
public record PointResponse(
	@Schema(description = "잔액 포인트", example = "1000")
	Integer balance
) {

	public static PointResponse toDto(Point point) {
		return PointResponse.builder()
			.balance(point.getBalance())
			.build();
	}

}
