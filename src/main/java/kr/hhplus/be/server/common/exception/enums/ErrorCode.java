package kr.hhplus.be.server.common.exception.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "토큰이 존재하지 않습니다."),
	INVALID_TOKEN(HttpStatus.NOT_FOUND, "유효 하지 않은 토큰"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
	INVALID_DATE(HttpStatus.NOT_FOUND, "유효 하지 않은 날짜"),
	POINT_NOT_FOUND(HttpStatus.NOT_FOUND, "포인트가 존재하지 않습니다."),
	CONCERT_SEAT_EXIST(HttpStatus.BAD_REQUEST, "이미 예약된 좌석입니다."),
	SEAT_NOT_FOUND(HttpStatus.NOT_FOUND, "좌석이 존재하지 않습니다."),
	CONCERT_NOT_FOUND(HttpStatus.NOT_FOUND, "콘서트가 존재하지 않습니다."),
	NOT_ENOUGH_BALANCE(HttpStatus.NOT_FOUND, "포인트 잔액이 충분하지 않습니다."),
	RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 좌석 예약이 존재하지 않습니다."),
	RESERVATION_TIME_OUT(HttpStatus.BAD_REQUEST, "좌석 예약 시간이 만료 되었습니다."),
	PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "결제가 존재하지 않습니다."),
	PAYMENT_FINISHED(HttpStatus.BAD_REQUEST, "이미 결제 완료되었습니다."),
	DISTRIBUTE_LOCK_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "분산 락 오류"),
	;

	private final HttpStatus status;
	private final String message;
}
