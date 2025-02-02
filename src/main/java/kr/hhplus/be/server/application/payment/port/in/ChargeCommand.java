package kr.hhplus.be.server.application.payment.port.in;

public record ChargeCommand(Long userId, Integer point) {
}
