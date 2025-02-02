package kr.hhplus.be.server.support.exception;

import kr.hhplus.be.server.support.exception.enums.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private final ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
