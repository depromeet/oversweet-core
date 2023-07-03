package com.depromeet.oversweet.member.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import com.depromeet.oversweet.exception.member.MissingRequiredInfoException;
import com.depromeet.oversweet.member.dto.KakaoTokenRequest;
import com.depromeet.oversweet.member.dto.MemberKakaoUserInfoResponse;
import com.depromeet.oversweet.member.dto.NicknameValidationResponse;
import com.depromeet.oversweet.member.dto.SignUpRequest;
import com.depromeet.oversweet.member.dto.SignUpResponse;
import com.depromeet.oversweet.member.dto.SocialSignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.depromeet.oversweet.exception.ErrorCode.MISSING_REQUIRED_INFO_ERROR;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberPureService memberPureService;
    private final MemberKakaoService memberKakaoService;

    public NicknameValidationResponse checkNicknameDuplicated(String nickname) {
        MemberEntity member = memberPureService.searchMemberByNickname(nickname);
        String memberNickName = member == null ? null : member.getNickname();
        return NicknameValidationResponse.builder()
                .nickname(memberNickName)
                .build();
    }

    public SocialSignInResponse signInKakao(KakaoTokenRequest kakaoTokenRequest) {
        MemberKakaoUserInfoResponse kakaoUserInfoResponse = memberKakaoService.searchKakaoUserInfo(kakaoTokenRequest);

        String socialId = kakaoUserInfoResponse.id();
        String nickname = String.valueOf(kakaoUserInfoResponse.properties().get("nickname"));
        String email = String.valueOf(kakaoUserInfoResponse.kakaoAccount().get("email"));

        return this.signIn(socialId, nickname, email, SocialProvider.KAKAO);
    }

    public SocialSignInResponse signIn(String socialId, String nickname, String email, SocialProvider socialProvider) {
        MemberEntity member = memberPureService.searchMemberBySocialId(socialId);
        //필수 정보가 없을 경우 401 에러
        //고유 ID 를 클라이언트가 가지고 있으면 추가 정보 기입 화면으로 이동
        //하지만, 고유 ID 를 클라이언트가 가지고 있을 수 없다면 로그인 화면으로 이동하는데 이 경우 계속 이 오류를 만나게 되므로 member 를 삭제하는 것으로 로직 수정
        if (member != null && member.checkRequiredInfoExist()) {
            memberPureService.removeMember(member.getId());
            throw new MissingRequiredInfoException(MISSING_REQUIRED_INFO_ERROR);
        }

        //신규 가입
        boolean isNewMember = false;
        if (member == null) {
            isNewMember = true;
            member = MemberEntity.builder()
                    .socialId(socialId)
                    .nickname(nickname)
                    .email(email)
                    .socialProvider(socialProvider)
                    .build();
            memberPureService.saveMember(member);
        }

        return new SocialSignInResponse(member.getId(), isNewMember);
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        MemberEntity member = memberPureService.searchMemberById(signUpRequest.id());
        member.updateRequiredInfo(signUpRequest.gender(), signUpRequest.weight(),
                signUpRequest.height(), signUpRequest.age(), signUpRequest.nickname());

        memberPureService.modifyMember(member);

        return SignUpResponse.builder()
                .dailySugar(member.getDailySugar())
                .build();
    }
}
