package com.conseller.conseller.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpDto {

    private String userId;
    private String userPassword;
    private String userEmail;
    private String userPhoneNumber;
    private String userNickname;
    private String userAccount;
    private String userAccountBank;
}
