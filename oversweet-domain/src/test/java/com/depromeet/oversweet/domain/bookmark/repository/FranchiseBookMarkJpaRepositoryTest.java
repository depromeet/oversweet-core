package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.config.JpaEntityConfig;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FranchiseJpaRepository;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.domain.member.repository.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  @DataJpaTest 는 JpaTest에 필요한 최소한의 빈을 불러오는데, 거기에는 @Configuration 빈이 포함되어있지 않다.
 *  따라서 @Configuration을 사용하여 따로 JpaConfig 파일을 만들었을 경우,
 *  @Import(JpaConfig.class) 를 통해 JpaConfig 파일을 불러와야 한다.
 */
@DataJpaTest
@Import(JpaEntityConfig.class)
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
                .build();

        memberJpaRepository.save(memberEntity);

        FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .id(1L)
                .name("스타벅스")
                .build();

        franchiseJpaRepository.save(franchiseEntity);

        FranchiseBookmarkEntity franchiseBookmarkEntity = FranchiseBookmarkEntity.builder()
                .member(memberEntity)
                .franchise(franchiseEntity)
                .build();

        franchiseBookMarkJpaRepository.save(franchiseBookmarkEntity);
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