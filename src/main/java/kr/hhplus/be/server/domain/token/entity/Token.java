package kr.hhplus.be.server.domain.token.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column
    @Enumerated(EnumType.STRING)
    private TokenStatus status;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    public enum TokenStatus {
        WAIT, ACTIVE, EXPIRED
    }

    @Builder
    public Token(Long tokenId, Long userId, TokenStatus status, LocalDateTime expiredAt) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.status = status;
        this.expiredAt = expiredAt;
    }

    public static Token createToken(Long userId) {
        return Token.builder()
                .userId(userId)
                .status(TokenStatus.WAIT)
                .expiredAt(LocalDateTime.now().plusMinutes(10))
                .build();
    }

    public void updateActiveStatus() {
        this.status = TokenStatus.ACTIVE;
    }

    public void updateExpiredStatus() {
        this.status = TokenStatus.EXPIRED;
    }
}
