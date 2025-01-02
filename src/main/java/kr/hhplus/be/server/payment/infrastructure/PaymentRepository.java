package kr.hhplus.be.server.payment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.payment.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
