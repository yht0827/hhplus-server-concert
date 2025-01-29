package kr.hhplus.be.server.interfaces.point.port.out;

import kr.hhplus.be.server.domain.point.entity.Point;
import lombok.Builder;

@Builder
public record SearchPointResponse(Integer balance) {

	public static SearchPointResponse toDto(Point point) {
		return SearchPointResponse.builder()
			.balance(point.getBalance())
			.build();
	}

}
