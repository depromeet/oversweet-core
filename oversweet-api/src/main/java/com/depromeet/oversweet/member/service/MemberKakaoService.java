package com.depromeet.oversweet.member.service;

import com.depromeet.KakaoFeignClient;
import com.depromeet.oversweet.MemberKakaoUserInfoResponse;
import com.depromeet.oversweet.member.dto.request.KakaoTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@Service
@RequiredArgsConstructor
public class MemberKakaoService {

    private final KakaoFeignClient kakaoFeignClient;

    public MemberKakaoUserInfoResponse searchKakaoUserInfo(KakaoTokenRequest kakaoTokenRequest) {
        return kakaoFeignClient.findKakaoUserInfo(
                APPLICATION_FORM_URLENCODED_VALUE, "Bearer " + kakaoTokenRequest.accessToken()
        );
    }
}
