package com.depromeet.oversweet.member.controller;

import com.depromeet.oversweet.annotation.SecurityExclusion;
import com.depromeet.oversweet.member.dto.KakaoTokenRequest;
import com.depromeet.oversweet.member.dto.SignUpRequest;
import com.depromeet.oversweet.member.dto.SignUpResponse;
import com.depromeet.oversweet.member.dto.SocialSignInResponse;
import com.depromeet.oversweet.member.service.MemberFacade;
import com.depromeet.oversweet.response.DataResponse;
import com.depromeet.oversweet.response.MessageResponse;
import com.depromeet.oversweet.security.jwt.JwtTokenProvider;
import com.depromeet.oversweet.security.jwt.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Slf4j
@Tag(name = "회원", description = "회원 관련 API")
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "닉네임 중복 체크", description = "닉네임 중복 체크 API 입니다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "닉네임 사용 가능")})
    @SecurityExclusion
    @PostMapping("/validation/nickname")
    public ResponseEntity<MessageResponse> checkNicknameDuplicated(@RequestParam String nickname) {
        memberFacade.checkNicknameDuplicated(nickname);
        return ResponseEntity.ok().body(MessageResponse.of(HttpStatus.OK, "닉네임 사용 가능"));
    }

    @Operation(summary = "카카오 회원가입 및 로그인 성공", description = "카카오 로그인 API 입니다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "카카오 회원가입 및 로그인 성공")})
    @SecurityExclusion
    @PostMapping("/kakao")
    public ResponseEntity<DataResponse<SocialSignInResponse>> kakaoSignIn(@RequestBody final KakaoTokenRequest kakaoTokenRequest) {
        SocialSignInResponse socialSignInResponse = memberFacade.signInKakao(kakaoTokenRequest);
        HttpHeaders headers = new HttpHeaders();

        if (socialSignInResponse.isRequiredInfoExist()) {
            TokenResponse tokenResponse = jwtTokenProvider.generateJwtToken(socialSignInResponse.id());
            headers.add(AUTHORIZATION, "Bearer " + tokenResponse.accessToken());
            headers.add(SET_COOKIE, tokenResponse.refreshToken());
        }

        return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED, "카카오 회원가입 및 로그인 성공", socialSignInResponse),
                headers, HttpStatus.CREATED);
    }

    @Operation(summary = "추가 정보 기입 후 회원가입", description = "추가 정보 기입 후 회원가입 API 입니다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "추가 정보 기입 후 회원가입 성공")})
    @SecurityExclusion
    @PostMapping("/auth")
    public ResponseEntity<DataResponse<SignUpResponse>> signUp(@RequestBody @Valid final SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = memberFacade.signUp(signUpRequest);
        TokenResponse tokenResponse = jwtTokenProvider.generateJwtToken(signUpResponse.id());

        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, "Bearer " + tokenResponse.accessToken());
        headers.add(SET_COOKIE, tokenResponse.refreshToken());

        return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED, "추가 정보 기입 후 회원가입 성공", signUpResponse),
                headers, HttpStatus.CREATED);
    }

}
