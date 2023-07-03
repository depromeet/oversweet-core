package com.depromeet.oversweet.member.service;

import com.depromeet.oversweet.config.KakaoFeignClient;
import com.depromeet.oversweet.member.dto.KakaoTokenRequest;
import com.depromeet.oversweet.member.dto.MemberKakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberKakaoService {

    private static final String BEARER = "Bearer ";

    private final KakaoFeignClient kakaoFeignClient;

    public MemberKakaoUserInfoResponse searchKakaoUserInfo(KakaoTokenRequest kakaoTokenRequest) {
        MemberKakaoUserInfoResponse kakaoUserInfo = kakaoFeignClient.findKakaoUserInfo(
                APPLICATION_FORM_URLENCODED_VALUE, BEARER + kakaoTokenRequest.accessToken()
        );
        log.info("searchKakaoUserInfo : {}", kakaoUserInfo);
        return kakaoUserInfo;
    }
}
