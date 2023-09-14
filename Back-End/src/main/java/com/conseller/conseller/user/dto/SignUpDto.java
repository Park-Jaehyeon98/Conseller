package com.conseller.conseller.user.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.*;

@ToString
@Getter @Setter @Builder
@AllArgsConstructor
public class SignUpDto {

    @NotBlank(message = "아이디를 입력해야 합니다.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$", message = "비밀번호는 영문, 숫자, 특수기호 포함 8자리 이상이여야 합니다.")
    private String userPassword;

    @NotBlank(message = "이메일을 입력해야 합니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String userEmail;

    @NotBlank(message = "핸드폰 번호를 입력해야 합니다.")
    private String userPhoneNumber;

    @NotBlank(message = "별명을 입력해야 합니다.")
    private String userNickname;

    @NotNull
    private Character userGender;

    @Positive(message = "나이는 정수로 입력이 가능합니다.")
    @Min(1)
    private Integer userAge;

    @NotBlank(message = "이름을 입력해야 합니다.")
    private String userName;

    @NotBlank(message = "계좌번호를 입력해야 합니다.")
    private String userAccount;

    @NotBlank(message = "거래은행을 선택해야 합니다.")
    private String userAccountBank;
}
