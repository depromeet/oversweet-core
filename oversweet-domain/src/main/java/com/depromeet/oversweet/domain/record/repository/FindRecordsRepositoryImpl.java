package com.depromeet.oversweet.domain.record.repository;

import static com.depromeet.oversweet.domain.drink.entity.QDrinkEntity.drinkEntity;
import static com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity.franchiseEntity;
import static com.depromeet.oversweet.domain.record.entity.QRecordEntity.recordEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.depromeet.oversweet.exception.ErrorCode;
import com.depromeet.oversweet.exception.record.NotFoundRecordException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.oversweet.domain.record.dto.QRankingDrink;
import com.depromeet.oversweet.domain.record.dto.RankingDrink;
import com.depromeet.oversweet.domain.record.entity.RecordEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

/**
 * 유저의 해당 기간 음료 당 조회 구현체
 * QueryDsl로 레코드 엔티티와 관련된 Drink, Franchise를 패치조인으로 가져온다
 * 참고 블로그 https://jojoldu.tistory.com/457?category=637935
 */
@Repository
public class FindRecordsRepositoryImpl implements FindRecordsRepository {

    private final JPAQueryFactory queryFactory;
    private final RecordJpaRepository recordJpaRepository;

    public FindRecordsRepositoryImpl(final EntityManager em, final RecordJpaRepository recordJpaRepository) {
        this.queryFactory = new JPAQueryFactory(em);
        this.recordJpaRepository = recordJpaRepository;
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

    /**
     * 해당 기간 내 인기 음료 조회
     *
     * @param franchiseId 프랜차이즈 ID
     * @param startDate   조회 시작 날짜
     * @param endDate     조회 종료 날짜
     * @return 인기 음료 리스트
     */
    @Override
    @Transactional(readOnly = true)
    public List<RankingDrink> findPopularDrinkRecordsByFranchiseId(Long franchiseId, LocalDateTime startDate, LocalDateTime endDate) {
        // 상위 3개만 조회
        return queryFactory.select(new QRankingDrink(
                        drinkEntity.id,
                        drinkEntity.imageUrl,
                        drinkEntity.name
                ))
                .from(recordEntity)
                .join(recordEntity.drink, drinkEntity)
                .join(drinkEntity.franchise, franchiseEntity)
                .where(franchiseEntity.id.eq(franchiseId))
                .where(recordEntity.createdAt.between(startDate, endDate))
                .groupBy(drinkEntity.id, drinkEntity.imageUrl, drinkEntity.name)
                .orderBy(recordEntity.count().desc())
                .limit(3)
                .fetch();
    }

    @Override
    @Transactional
    public RecordEntity findRecordByMemberIdAndDrinkId(final Long memberId, final Long drinkId) {
        return recordJpaRepository.findByMemberIdAndDrinkId(memberId, drinkId)
                .orElseThrow(() -> new NotFoundRecordException(ErrorCode.NOT_FOUND_RECORD));
    }
}

