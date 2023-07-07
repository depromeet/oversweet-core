package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.depromeet.oversweet.domain.drink.entity.QDrinkEntity.drinkEntity;
import static com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity.franchiseEntity;
import static com.depromeet.oversweet.domain.record.entity.QRecordEntity.recordEntity;

/**
 * 유저의 해당 기간 음료 당 조회 구현체
 * QueryDsl로 레코드 엔티티와 관련된 Drink, Franchise를 패치조인으로 가져온다
 * 참고 블로그 https://jojoldu.tistory.com/457?category=637935
 */
@Repository
public class FindRecordsRepositoryImpl implements FindRecordsRepository {

    private final JPAQueryFactory queryFactory;

    public FindRecordsRepositoryImpl(final EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecordEntity> findRecordsByLocalDateTime(final Long memberId, final LocalDateTime startDate, final LocalDateTime endDate) {

        return queryFactory.selectFrom(recordEntity)
                .join(recordEntity.drink, drinkEntity).fetchJoin() // xxToOne
                .join(recordEntity.drink.franchise, franchiseEntity).fetchJoin() // xxToOne
                .where(recordEntity.member.id.eq(memberId))
                .where(recordEntity.createdAt.between(startDate, endDate))
                .fetch();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RecordEntity> findRecordById(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(recordEntity)
                .join(recordEntity.drink, drinkEntity).fetchJoin()
                .join(recordEntity.drink.franchise, franchiseEntity).fetchJoin()
                .where(recordEntity.member.id.eq(id))
                .fetchOne());
    }
}

