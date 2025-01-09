package kr.hhplus.be.server.token.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenStatus {

	WAIT("wait"),
	ACTIVE("active"),
	EXPIRED("expired");

	private final String status;
}
