package com.depromeet.oversweet.member.controller;

import com.depromeet.oversweet.member.dto.request.KakaoTokenRequest;
import com.depromeet.oversweet.member.dto.response.SignInResponse;
import com.depromeet.oversweet.member.service.MemberFacade;
import com.depromeet.oversweet.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "회원", description = "회원 관련 API")
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;

    @Operation(summary = "카카오 회원가입 및 로그인", description = "카카오 회원가입 및 로그인 API 입니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "카카오 회원가입 및 로그인 성공")
    })
    @PostMapping("/kakao")
    public ResponseEntity<DataResponse<SignInResponse>> kakaoSignIn(@RequestBody final KakaoTokenRequest kakaoTokenRequest) {
        SignInResponse signInResponse = memberFacade.signInKakao(kakaoTokenRequest);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "응답 성공", signInResponse), HttpStatus.OK);
    }

//    @Operation(summary = "닉네임 중복 체크", description = "닉네임 중복 체크 API 입니다.")
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200", description = "닉네임이 유무를 반환")
//    })
//    @PostMapping("/validation")
//    public ResponseEntity<DataResponse<SignInResponse>> checkNicknameDuplicated(@RequestParam String nickname) {
////        SignInResponse signInResponse = memberFacade.signInKakao();
////        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "응답 성공", signInResponse), HttpStatus.OK);
//        return null;
//    }

}
