package kr.hhplus.be.server.support.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<?> handleCustomException(final CustomException exception) {
		return ResponseEntity.status(exception.getErrorCode().getStatus()).body(exception.getMessage());
	}
}
