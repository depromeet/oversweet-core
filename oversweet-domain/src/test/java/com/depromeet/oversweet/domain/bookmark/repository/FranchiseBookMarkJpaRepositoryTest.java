package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FranchiseJpaRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.domain.member.repository.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = {FranchiseBookMarkJpaRepository.class, MemberJpaRepository.class, FranchiseJpaRepository.class})
class FranchiseBookMarkJpaRepositoryTest {

    private final FranchiseBookMarkJpaRepository franchiseBookMarkJpaRepository;

    private final MemberJpaRepository memberJpaRepository;

    private final FranchiseJpaRepository franchiseJpaRepository;

    @Autowired
    public FranchiseBookMarkJpaRepositoryTest(FranchiseBookMarkJpaRepository franchiseBookMarkJpaRepository,
                                              MemberJpaRepository memberJpaRepository,
                                              FranchiseJpaRepository franchiseJpaRepository) {
        this.franchiseBookMarkJpaRepository = franchiseBookMarkJpaRepository;
        this.memberJpaRepository = memberJpaRepository;
        this.franchiseJpaRepository = franchiseJpaRepository;
    }

    @BeforeEach
    void setUp() {
        // DataJpaTest 를 사용하기 위해 entity의 필수값을 모두 넣어줘야 한다.
        // createdAt, updatedAt 은 Spring Boot Test가 아닌 DataJpaTest에 의해 값을 채워넣지 못 해 직접 넣어줘야 한다.
        MemberEntity memberEntity = MemberEntity.builder()
                .id(1L)
                .socialId("socialId")
                .socialProvider(SocialProvider.KAKAO)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        memberJpaRepository.save(memberEntity);

        FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .id(1L)
                .name("스타벅스")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        franchiseJpaRepository.save(franchiseEntity);

        franchiseBookMarkJpaRepository.save(new FranchiseBookmarkEntity(memberEntity, franchiseEntity, LocalDateTime.now(), LocalDateTime.now()));
    }

    @Test
    void 유저의_고유_ID를_통해서_즐겨찾기한_프랜차이즈_목록_조회() {
        // given
        long memberId = 1L;

        // when
        List<FranchiseBookmarkEntity> bookMarks = franchiseBookMarkJpaRepository.findByMemberId(memberId);

        // then
        assertThat(bookMarks).hasSize(1);
    }
}