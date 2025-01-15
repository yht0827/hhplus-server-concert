package kr.hhplus.be.server.common.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    INVALID_DATE(HttpStatus.NOT_FOUND, "유효 하지 않은 날짜"),
    ;

    private final HttpStatus status;
    private final String message;
}
