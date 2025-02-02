package kr.hhplus.be.server.interfaces.concert.port.in;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.hhplus.be.server.support.exception.CustomException;
import kr.hhplus.be.server.support.exception.enums.ErrorCode;

public record ConcertDateRequest(
	@Schema(description = "날짜", example = "20250125")
	String date
) {
	public ConcertDateRequest {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate.parse(date, formatter);
		} catch (DateTimeParseException ignored) {
			throw new CustomException(ErrorCode.INVALID_DATE);
		}
	}
}
