package kr.hhplus.be.server.reserve.presentation.dto;

public record ReserveRequest(String uuid, Long concertId, String date, Integer seat) {

}
