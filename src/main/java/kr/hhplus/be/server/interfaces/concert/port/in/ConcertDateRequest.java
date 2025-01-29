package kr.hhplus.be.server.interfaces.concert.port.in;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.enums.ErrorCode;

public record ConcertDateRequest(String date) {

	public ConcertDateRequest {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate.parse(date, formatter);
		} catch (DateTimeParseException ignored) {
			throw new CustomException(ErrorCode.INVALID_DATE);
		}
	}
}
