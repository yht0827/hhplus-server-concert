package kr.hhplus.be.server.token.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.hhplus.be.server.common.BaseEntity;
import kr.hhplus.be.server.user.domain.User;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	@Enumerated(EnumType.STRING)
	private TokenStatus status;

	@Column(name = "expired_at")
	private LocalDateTime expiredAt;

	@Builder
	public Token(Long tokenId, User user, TokenStatus status, LocalDateTime expiredAt) {
		this.tokenId = tokenId;
		this.user = user;
		this.status = status;
		this.expiredAt = expiredAt;
	}

	public static Token createToken(User user) {
		return Token.builder()
			.user(user)
			.status(TokenStatus.WAIT)
			.expiredAt(LocalDateTime.now().plusMinutes(10))
			.build();
	}

	public void updateStatus(TokenStatus status) {
		this.status = status;
	}

	public void updateExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

}
