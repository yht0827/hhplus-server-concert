package kr.hhplus.be.server.domain.concert.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "concert_seat_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertSeatCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "concert_seat_category_id")
	private Long concertSeatCategoryId;

	@Column
	@Enumerated(EnumType.STRING)
	private Level level;

	@Column
	private Integer price;

	public enum Level {
		STANDARD, PREMIUM, VIP
	}

	@Builder
	public ConcertSeatCategory(Long concertSeatCategoryId, Level level, Integer price) {
		this.concertSeatCategoryId = concertSeatCategoryId;
		this.level = level;
		this.price = price;
	}
}
