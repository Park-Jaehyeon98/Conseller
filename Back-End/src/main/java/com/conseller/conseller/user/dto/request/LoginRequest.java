package com.conseller.conseller.user.dto.request;

import lombok.*;

@ToString
@Getter @Setter @Builder
@AllArgsConstructor
public class LoginRequest {

    private String userId;
    private String userPassword;

}
