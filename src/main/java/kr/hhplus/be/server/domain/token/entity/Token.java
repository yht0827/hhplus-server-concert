package kr.hhplus.be.server.domain.token.entity;

import java.time.LocalDateTime;

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

@Entity
@Getter
@Table(name = "token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "token_id")
	private Long tokenId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "token_status")
	@Enumerated(EnumType.STRING)
	private TokenStatus tokenStatus;

	@Column(name = "expired_at")
	private LocalDateTime expiredAt;

	public enum TokenStatus {
		WAIT, ACTIVE, EXPIRED
	}

	@Builder
	public Token(final Long tokenId, final Long userId, final TokenStatus tokenStatus, final LocalDateTime expiredAt) {
		this.tokenId = tokenId;
		this.userId = userId;
		this.tokenStatus = tokenStatus;
		this.expiredAt = expiredAt;
	}

	public static Token createToken(final Long userId) {
		return Token.builder()
			.userId(userId)
			.tokenStatus(TokenStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(10))
			.build();
	}

	public void updateExpiredStatus() {
		this.tokenStatus = TokenStatus.EXPIRED;
	}
}
