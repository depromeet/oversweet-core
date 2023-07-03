package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.FranchiseBookmarkEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.exception.bookmark.AlreadyFranchiseBookMarked;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.depromeet.oversweet.exception.ErrorCode.ALREADY_FRANCHISE_BOOK_MARKED;

/**
 * 프랜차이즈 즐겨찾기 조회 레포지토리
 * 주로 optional 처리를 담당하며, 상위 service 계층에는 optional을 반환하지 않는다.
 */
@Repository
@RequiredArgsConstructor
public class FindFranchiseBookMarkRepositoryImpl implements FindFranchiseBookMarkRepository {

    private final FranchiseBookMarkJpaRepository franchiseBookMarkJpaRepository;

    /**
     * 유저가 즐겨 찾기한 프랜차이즈 Entity 목록 조회
     *
     * @param memberId 유저 ID
     * @return 즐겨찾기한 프랜차이즈 Entity 목록
     */
    @Override
    @Transactional(readOnly = true)
    public List<FranchiseBookmarkEntity> findFranchiseBookMarkByMemberId(final Long memberId) {
        return franchiseBookMarkJpaRepository.findByMemberId(memberId);
    }

    /**
     * 유저가 즐겨 찾기 하고자 하는 프랜차이즈가 이미 등록된 상태인지 확인
     * 이미 등록된 상태라면 예외 발생
     *
     * @param member    유저 Entity
     * @param franchise 프랜차이즈 Entity
     */
    @Override
    @Transactional(readOnly = true)
    public void validateAlreadyFranchiseBookMarked(MemberEntity member, FranchiseEntity franchise) {
        if (franchiseBookMarkJpaRepository.existsByMemberAndFranchise(member, franchise)) {
            throw new AlreadyFranchiseBookMarked(ALREADY_FRANCHISE_BOOK_MARKED);
        }
    }
}
