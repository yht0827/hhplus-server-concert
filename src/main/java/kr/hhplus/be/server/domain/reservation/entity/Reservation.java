package kr.hhplus.be.server.domain.reservation.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseEntity;
import kr.hhplus.be.server.domain.concert.entity.ConcertSeat;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private ConcertSeat concertSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public enum ReservationStatus {
        WAIT, RESERVED
    }

    @Builder
    public Reservation(Long reservationId, ConcertSeat concertSeat, Payment payment, ReservationStatus status, User user) {
        this.reservationId = reservationId;
        this.concertSeat = concertSeat;
        this.payment = payment;
        this.status = status;
        this.user = user;
    }
}
