package com.depromeet.oversweet.domain.record.repository;

import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.depromeet.oversweet.domain.drink.entity.QDrinkEntity.drinkEntity;
import static com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity.franchiseEntity;
import static com.depromeet.oversweet.domain.member.entity.QMemberEntity.memberEntity;
import static com.depromeet.oversweet.domain.record.entity.QRecordEntity.recordEntity;

/**
 * 유저의 데일리(하루) 음료 조회 구현체
 * QueryDsl로 레코드 엔티티와 관련된 Drink, Franchise를 패치조인으로 가져온다
 * 참고 블로그 https://jojoldu.tistory.com/457?category=637935
 */
@Repository
public class FindDailyRecordsRepositoryImpl implements FindDailyRecordsRepository {

    private final JPAQueryFactory queryFactory;

    public FindDailyRecordsRepositoryImpl(final EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<RecordEntity> findDailyRecordsByLocalDatetime(final Long memberId, final LocalDateTime startDate, final LocalDateTime endDate) {

        return queryFactory.selectFrom(recordEntity)
                .join(recordEntity.member, memberEntity).fetchJoin() // xxToOne
                .join(recordEntity.drink, drinkEntity).fetchJoin() // xxToOne
                .join(recordEntity.drink.franchise, franchiseEntity).fetchJoin() // xxToOne
                .where(memberEntity.id.eq(memberId))
                .where(recordEntity.createdAt.between(startDate, endDate))
                .fetch();

    }
}
