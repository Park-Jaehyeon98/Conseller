package com.conseller.conseller.user.dto.response;

import lombok.*;

@Builder
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoResponse {

    private String userId;
    private String userPassword;
    private String userNickname;
    private String userEmail;
    private String userAccount;
    private String userAccountBank;
    private String userPhoneNumber;

}
