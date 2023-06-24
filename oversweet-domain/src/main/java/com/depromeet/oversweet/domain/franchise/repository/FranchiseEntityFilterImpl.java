package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity.franchiseEntity;

/**
 * 프랜차이즈를 필터링을 통해서 조회하는 커스텀 인터페이스의 구현체
 */
public class FranchiseEntityFilterImpl implements FranchiseEntityFilter {

    private final JPAQueryFactory queryFactory;

    public FranchiseEntityFilterImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 프랜차이즈 아이디 목록을 통해 프랜차이즈 목록을 조회
     * @param franchiseIds 프랜차이즈 아이디 목록
     * @return 프랜차이즈 목록
     */
    @Override
    public List<FranchiseEntity> findAllByFranchiseIds(List<Long> franchiseIds) {
        return queryFactory
                .selectFrom(franchiseEntity)
                .where(idsIn(franchiseIds))
                .fetch();
    }

    /**
     * 프랜차이즈 아이디 목록을 통해 프랜차이즈 목록을 조회하는 쿼리 조건
     * @param franchiseIds 프랜차이즈 아이디 목록
     */
    private BooleanExpression idsIn(List<Long> franchiseIds) {
        return franchiseEntity.id.in(franchiseIds);
    }
}
