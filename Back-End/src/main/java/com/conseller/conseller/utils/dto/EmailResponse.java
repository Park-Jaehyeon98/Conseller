package com.conseller.conseller.utils.dto;

import lombok.*;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EmailResponse {

    private String userName;
    private String userEmail;
    private String userPassword;

}
