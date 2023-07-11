package com.depromeet.oversweet.domain.bookmark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;

public interface FranchiseBookMarkJpaRepository extends JpaRepository<FranchiseBookmarkEntity, Long> {
    List<FranchiseBookmarkEntity> findByMemberId(final Long memberId);

    boolean existsByMemberAndFranchise(MemberEntity member, FranchiseEntity franchise);

    void deleteByMemberAndFranchise(MemberEntity member, FranchiseEntity franchise);

    boolean existsByMember_IdAndFranchise_Id(Long memberId, Long franchiseId);
}
