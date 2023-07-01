package com.depromeet.oversweet.member.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.member.dto.request.KakaoTokenRequest;
import com.depromeet.oversweet.MemberKakaoUserInfoResponse;
import com.depromeet.oversweet.member.dto.response.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberPureService memberPureService;
    private final MemberKakaoService memberKakaoService;

    public SignInResponse signInKakao(KakaoTokenRequest kakaoTokenRequest) {
        MemberKakaoUserInfoResponse kakaoUserInfoResponse = memberKakaoService.searchKakaoUserInfo(kakaoTokenRequest);

        String socialId = kakaoUserInfoResponse.getId();
        String nickname = String.valueOf(kakaoUserInfoResponse.getProperties().get("nickname"));

        return this.signIn(socialId, nickname, SocialProvider.KAKAO);
    }

    public SignInResponse signIn(String socialId, String nickname, SocialProvider socialProvider) {
        MemberEntity member = memberPureService.findBySocialId(socialId);
        boolean isExist = true;
        if (member == null) {
            isExist = false;
//            member = new MemberEntity(socialId, nickname, socialProvider);
            memberPureService.save(member);
        }

        return SignInResponse.builder()
                .nickname(nickname)
                .isExist(isExist)
                .build();
    }
}
