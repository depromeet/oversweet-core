package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FranchiseBookMarkJpaRepository extends JpaRepository<FranchiseBookmarkEntity, Long> {
    List<FranchiseBookmarkEntity> findByMemberId(final Long memberId);

    boolean existsByMemberAndFranchise(MemberEntity member, FranchiseEntity franchise);

    void deleteByMemberAndFranchise(MemberEntity member, FranchiseEntity franchise);
}
