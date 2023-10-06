package com.conseller.conseller.user.dto.request;

import lombok.*;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PasswordRequest {
    private String userPassword;
}
