package kr.hhplus.be.server.application.payment.port.in;

public record PaymentCommand(Long reservationId, Long userId, Long concertId, Integer price) {
}
