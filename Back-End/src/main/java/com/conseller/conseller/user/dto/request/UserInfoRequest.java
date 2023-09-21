package com.conseller.conseller.user.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Getter @Setter @ToString
@AllArgsConstructor
public class UserInfoRequest {

    @NotBlank(message = "비밀번호를 입력해야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$", message = "비밀번호는 영문, 숫자, 특수기호 포함 8자리 이상이여야 합니다.")
    private String userPassword;

    @NotBlank(message = "별명을 입력해야 합니다.")
    private String userNickname;

    @NotBlank(message = "이메일을 입력해야 합니다.")
    @Email
    private String userEmail;

    @NotBlank(message = "계좌번호를 입력해야 합니다.")
    private String userAccount;

    @NotBlank(message = "은행정보를 입력해야 합니다.")
    private String userAccountBank;

}
