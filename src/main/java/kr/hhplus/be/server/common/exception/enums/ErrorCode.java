package kr.hhplus.be.server.common.exception.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	USER_NOT_FOUND(HttpStatus.NO_CONTENT, "유저가 존재하지 않습니다.");

	private final HttpStatus status;
	private final String message;
}
