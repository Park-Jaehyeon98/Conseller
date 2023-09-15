package com.conseller.conseller.user.dto;

import lombok.*;

@ToString
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoValidationDto {
    private int status;
    private String message;
}
