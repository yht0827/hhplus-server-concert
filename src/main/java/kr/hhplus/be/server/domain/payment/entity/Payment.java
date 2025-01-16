package kr.hhplus.be.server.domain.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.server.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;

	@Column(name = "reservation_id")
	private Long reservationId;

	@Column(name = "concert_id")
	private Long concertId;

	@Column
	private Integer price;

	@Builder
	public Payment(Long paymentId, Long reservationId, Long concertId, Integer price) {
		this.paymentId = paymentId;
		this.reservationId = reservationId;
		this.concertId = concertId;
		this.price = price;
	}
}
