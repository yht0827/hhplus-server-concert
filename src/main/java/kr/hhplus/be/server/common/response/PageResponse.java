package kr.hhplus.be.server.common.response;

import java.util.List;

import org.springframework.data.domain.Page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PageResponse<T>(

	@Schema(description = "조회 리스트")
	List<T> list,

	@Schema(description = "총 페이지 수")
	Integer totalPages,

	@Schema(description = "한 페이지의 데이터 개수")
	Integer pageSize,

	@Schema(description = "총 데이터 개수")
	Long totalElements,

	@Schema(description = "현재 페이지 번호")
	Integer pageNumber
) {
	public static <T> PageResponse<T> toDto(Page<T> response) {
		return PageResponse.<T>builder()
			.list(response.getContent())
			.totalPages(response.getTotalPages())
			.pageSize(response.getSize())
			.totalElements(response.getTotalElements())
			.pageNumber(response.getNumber())
			.build();
	}
}
