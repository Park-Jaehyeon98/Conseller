package com.conseller.conseller.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter @Builder @ToString
@RequiredArgsConstructor
public class AccessTokenResponse {

    private final String accessToken;

}
