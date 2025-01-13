package kr.hhplus.be.server.token.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenStatus {

	WAIT("대기"),
	ACTIVE("활성"),
	EXPIRED("만료");

	private final String status;
}
