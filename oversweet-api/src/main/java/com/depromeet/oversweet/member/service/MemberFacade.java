package com.depromeet.oversweet.member.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.exception.member.DuplicateNicknameException;
import com.depromeet.oversweet.member.dto.KakaoTokenRequest;
import com.depromeet.oversweet.member.dto.MemberKakaoUserInfoResponse;
import com.depromeet.oversweet.member.dto.SignUpRequest;
import com.depromeet.oversweet.member.dto.SignUpResponse;
import com.depromeet.oversweet.member.dto.SocialSignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.depromeet.oversweet.exception.ErrorCode.NICKNAME_DUPLICATED_ERROR;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberPureService memberPureService;
    private final MemberKakaoService memberKakaoService;

    public void checkNicknameDuplicated(String nickname) {
        Optional<MemberEntity> member = memberPureService.searchMemberByNickname(nickname);
        if (member.isPresent()) {
            throw new DuplicateNicknameException(NICKNAME_DUPLICATED_ERROR);
        }
    }

    public SocialSignInResponse signInKakao(KakaoTokenRequest kakaoTokenRequest) {
        MemberKakaoUserInfoResponse kakaoUserInfoResponse = memberKakaoService.searchKakaoUserInfo(kakaoTokenRequest);

        String socialId = kakaoUserInfoResponse.id();
        String nickname = String.valueOf(kakaoUserInfoResponse.properties().get("nickname"));
        String email = String.valueOf(kakaoUserInfoResponse.kakaoAccount().get("email"));

        return signIn(socialId, nickname, email, SocialProvider.KAKAO);
    }

    public SocialSignInResponse signIn(String socialId, String nickname, String email, SocialProvider socialProvider) {
        Optional<MemberEntity> memberOptional = memberPureService.searchMemberBySocialId(socialId);
        MemberEntity member;

        //정보가 있을 경우 member 의 id 와 필수 정보 기입 여부 반환
        if (memberOptional.isPresent()) {
            member = memberOptional.get();
            return new SocialSignInResponse(member.getId(), member.checkRequiredInfoExist());
        }

        //신규 가입인 경우 1차 가입 후 member 의 id 와 필수 정보 기입 여부에는 false 로 반환
        member = MemberEntity.builder()
                .socialId(socialId)
                .nickname(nickname)
                .email(email)
                .socialProvider(socialProvider)
                .build();
        memberPureService.saveMember(member);

        return new SocialSignInResponse(member.getId(), false);
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        MemberEntity member = memberPureService.searchMemberById(signUpRequest.id());
        member.updateRequiredInfo(signUpRequest.gender(), signUpRequest.weight(),
                signUpRequest.height(), signUpRequest.age(), signUpRequest.nickname());

        memberPureService.modifyMember(member);

        return SignUpResponse.builder()
                .id(member.getId())
                .dailySugar(member.getDailySugar())
                .build();
    }
}
