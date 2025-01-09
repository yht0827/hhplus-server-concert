package kr.hhplus.be.server.reservation.presentation.dto;

public record ReserveRequest(String uuid, Long concertId, String date, Integer seat) {

}
