package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FranchiseBookMarkJpaRepository extends JpaRepository<FranchiseBookmarkEntity, Long> {
    List<FranchiseBookmarkEntity> findByMemberId(final Long memberId);
}
