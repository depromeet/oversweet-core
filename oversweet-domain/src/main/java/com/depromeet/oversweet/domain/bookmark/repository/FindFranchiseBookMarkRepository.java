package com.depromeet.oversweet.domain.bookmark.repository;

import java.util.List;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;

/**
 * 즐겨 찾기한 프랜차이즈 조회 Interface
 */
public interface FindFranchiseBookMarkRepository {
    List<FranchiseBookmarkEntity> findFranchiseBookMarkByMemberId(final Long memberId);

    void validateAlreadyFranchiseBookMarked(MemberEntity member, FranchiseEntity franchise);

    boolean isFranchiseBookMarkedByMemberId(Long memberId, Long franchiseId);
}
