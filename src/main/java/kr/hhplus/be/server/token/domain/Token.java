package kr.hhplus.be.server.token.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.hhplus.be.server.config.common.BaseEntity;
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
}
