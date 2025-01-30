package kr.hhplus.be.server.infrastructure.token;

import static kr.hhplus.be.server.domain.token.entity.QToken.*;

import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import kr.hhplus.be.server.domain.token.entity.Token;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenCustomRepositoryImpl implements TokenCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private final EntityManager entityManager;

	@Override
	public List<Token> getAllWaitTokens() {
		return jpaQueryFactory.selectFrom(token)
			.where(token.tokenStatus.eq(Token.TokenStatus.WAIT))
			.fetch();
	}

	@Override
	public List<Token> getTimeoutTokens() {
		return jpaQueryFactory.selectFrom(token)
			.where(token.expiredAt.after(LocalDateTime.now()),
				token.tokenStatus.ne(Token.TokenStatus.EXPIRED))
			.fetch();
	}

	@Override
	public Long updateWaitTokens(final List<Long> ids) {

		// WAIT -> ACTIVE
		long count = jpaQueryFactory.update(token)
			.set(token.tokenStatus, Token.TokenStatus.ACTIVE)
			.where(token.tokenId.in(ids))
			.execute();

		// 영속성 컨텍스트 초기화
		entityManager.flush();
		entityManager.clear();

		return count;
	}

	@Override
	public Long updateExpireTokens(final List<Long> ids) {

		// WAIT, ACTIVE -> EXPIRED
		long count = jpaQueryFactory.update(token)
			.set(token.tokenStatus, Token.TokenStatus.EXPIRED)
			.where(token.tokenId.in(ids))
			.execute();

		// 영속성 컨텍스트 초기화
		entityManager.flush();
		entityManager.clear();

		return count;
	}

	@Override
	public Token findByUserId(final Long userId) {
		return jpaQueryFactory.selectFrom(token)
			.where(token.userId.eq(userId))
			.fetchOne();
	}

}
