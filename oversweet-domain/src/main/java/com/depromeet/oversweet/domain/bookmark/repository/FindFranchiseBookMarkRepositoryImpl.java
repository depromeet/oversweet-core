package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 프랜차이즈 즐겨찾기 조회 레포지토리
 * 주로 optional 처리를 담당하며, 상위 service 계층에는 optional을 반환하지 않는다.
 */
@Repository
@RequiredArgsConstructor
public class FindFranchiseBookMarkRepositoryImpl implements FindFranchiseBookMarkRepository {

    private final FranchiseBookMarkJpaRepository franchiseBookMarkJpaRepository;

    /**
     * 유저가 즐겨 찾기한 프랜차이즈 Entity 목록 조회
     *
     * @param memberId 유저 ID
     * @return 즐겨찾기한 프랜차이즈 Entity 목록
     */
    @Override
    @Transactional(readOnly = true)
    public List<FranchiseBookmarkEntity> findFranchiseBookMarkByMemberId(final Long memberId) {
        return franchiseBookMarkJpaRepository.findByMemberId(memberId);
    }
}
