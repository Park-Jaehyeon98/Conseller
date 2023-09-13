package com.conseller.conseller.user.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@Getter @Setter @Builder
@AllArgsConstructor
public class SignUpDto {

    @NotBlank
    @Size(min = 8)
    private String userId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$")
    private String userPassword;

    @NotBlank
    @Email
    private String userEmail;

    @NotBlank
    private String userPhoneNumber;

    @NotBlank
    @Size(min = 8)
    private String userNickname;

    @NotBlank
    private String userAccount;

    @NotBlank
    private String userAccountBank;
}
