package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
}
