package kr.hhplus.be.server.interfaces.point.port.out;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.domain.point.entity.Point;
import lombok.Builder;

@Builder
public record SearchPointResponse(
	@Schema(description = "사용자 ID", example = "1")
	Integer balance
) {

	public static SearchPointResponse toDto(Point point) {
		return SearchPointResponse.builder()
			.balance(point.getBalance())
			.build();
	}

}
