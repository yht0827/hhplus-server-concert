package kr.hhplus.be.server.reservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationStatus {

	WAIT("wait"),
	RESERVED("reserved");

	private final String status;
}


