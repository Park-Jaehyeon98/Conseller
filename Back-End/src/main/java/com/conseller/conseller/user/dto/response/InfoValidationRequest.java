package com.conseller.conseller.user.dto.response;

import lombok.*;

@ToString
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoValidationRequest {
    private int status;
    private String message;
}
