package com.depromeet.oversweet.domain.member.repository;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {


    Optional<MemberEntity> findByNickname(String nickname);

    Optional<MemberEntity> findBySocialId(String socialId);
}
