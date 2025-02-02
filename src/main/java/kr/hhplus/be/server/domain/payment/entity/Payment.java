package kr.hhplus.be.server.domain.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.server.support.entity.BaseEntity;
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

	@Column
	private Integer price;

	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@Column(name = "payment_type")
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	public enum PaymentType {
		POINT, CARD, TOSS
	}

	public enum PaymentStatus {
		PENDING, COMPLETED, CANCELLED
	}

	@Builder
	public Payment(final Long paymentId, final Long reservationId, final Integer price,
		final PaymentStatus paymentStatus, final PaymentType paymentType) {
		this.paymentId = paymentId;
		this.reservationId = reservationId;
		this.price = price;
		this.paymentStatus = paymentStatus;
		this.paymentType = paymentType;
	}

	public void updateStatus(PaymentStatus status) {
		this.paymentStatus = status;
	}

	public static Payment toEntity(final Long reservationId, final Integer price, final PaymentStatus paymentStatus,
		final PaymentType paymentType) {
		return Payment.builder()
			.reservationId(reservationId)
			.price(price)
			.paymentStatus(paymentStatus)
			.paymentType(paymentType)
			.build();
	}

}
