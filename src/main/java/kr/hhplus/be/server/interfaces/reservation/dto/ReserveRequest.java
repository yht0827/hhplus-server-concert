package kr.hhplus.be.server.interfaces.reservation.dto;

public record ReserveRequest(String uuid, Long concertId, String date, Integer seat) {

}
