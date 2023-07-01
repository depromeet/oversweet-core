package com.depromeet.oversweet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberKakaoUserInfoResponse {

    //todo setter 필요한가?

    private String id;

    @JsonProperty(value = "connected_at")
    private String connectedAt;

    private Map<String, Object> properties;

    @JsonProperty(value = "kakao_account")
    private Map<String, Object> kakaoAccount;
}
