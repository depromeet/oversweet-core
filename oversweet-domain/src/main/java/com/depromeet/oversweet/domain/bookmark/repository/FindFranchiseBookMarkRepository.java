package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;

import java.util.List;

/**
 * 즐겨 찾기한 프랜차이즈 조회 Interface
 */
public interface FindFranchiseBookMarkRepository {
    List<FranchiseBookmarkEntity> findFranchiseBookMarkByMemberId(final Long memberId);

    void validateAlreadyFranchiseBookMarked(MemberEntity member, FranchiseEntity franchise);
}
