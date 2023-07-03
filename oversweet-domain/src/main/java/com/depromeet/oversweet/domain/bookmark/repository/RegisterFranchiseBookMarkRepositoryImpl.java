package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 특정 프랜차이즈 즐겨찾기 등록 Repository
 * 주로 optional 처리를 담당하며, 상위 service 계층에는 optional을 반환하지 않는다.
 */
@Repository
@RequiredArgsConstructor
public class RegisterFranchiseBookMarkRepositoryImpl implements RegisterFranchiseBookMarkRepository {

    private final FranchiseBookMarkJpaRepository franchiseBookMarkJpaRepository;

    /**
     * 특정 프랜차이즈을 즐겨찾기에 등록한다.
     *
     * @param member API 요청자 Entity
     * @param franchise 즐겨찾기할 프랜차이즈 Entity
     */
    @Override
    @Transactional
    public void saveFranchiseBookmark(MemberEntity member, FranchiseEntity franchise) {
        FranchiseBookmarkEntity franchiseBookmark = new FranchiseBookmarkEntity(member, franchise);
        franchiseBookMarkJpaRepository.save(franchiseBookmark);
    }
}
