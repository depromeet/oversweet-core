package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;

/**
 * 특정 프랜차이즈 즐겨찾기 등록 Interface
 */
public interface RegisterFranchiseBookMarkRepository {

    void saveFranchiseBookmark(final MemberEntity member, final FranchiseEntity franchise);

    void deleteFranchiseBookmark(final MemberEntity member, final FranchiseEntity franchise);
}
