package kr.hhplus.be.server.infrastructure.token;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.hhplus.be.server.domain.token.entity.Token;
import kr.hhplus.be.server.domain.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static kr.hhplus.be.server.domain.token.entity.QToken.token;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public Token save(Token token) {
        return tokenJpaRepository.save(token);
    }

    @Override
    public List<Token> getAllWaitTokens() {
        return jpaQueryFactory.selectFrom(token)
                .where(token.status.eq(Token.TokenStatus.WAIT))
                .fetch();
    }

    @Override
    public List<Token> getTimeoutTokens() {
        return jpaQueryFactory.selectFrom(token)
                .where(token.expiredAt.before(LocalDateTime.now()))
                .fetch();
    }

    @Override
    public Long updateWaitTokens(List<Long> ids) {

        // WAIT -> ACTIVE
        long count = jpaQueryFactory.update(token)
                .set(token.status, Token.TokenStatus.ACTIVE)
                .where(token.tokenId.in(ids))
                .execute();

        // 영속성 컨텍스트 초기화
        entityManager.flush();
        entityManager.clear();

        return count;
    }

    @Override
    public Long updateExpireTokens(List<Long> ids) {

        // WAIT, ACTIVE -> EXPIRED
        long count = jpaQueryFactory.update(token)
                .set(token.status, Token.TokenStatus.EXPIRED)
                .where(token.tokenId.in(ids))
                .execute();

        // 영속성 컨텍스트 초기화
        entityManager.flush();
        entityManager.clear();

        return count;
    }
}
