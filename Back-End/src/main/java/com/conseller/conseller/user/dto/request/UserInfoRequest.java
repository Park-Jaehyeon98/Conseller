package com.conseller.conseller.user.dto.request;

import lombok.*;

@Builder
@Getter @Setter @ToString
@AllArgsConstructor
public class UserInfoRequest {

    private String userPassword;
    private String userNickname;
    private String userEmail;
    private String userAccount;
    private String userAccountBank;

}
