package com.conseller.conseller.utils;

import lombok.*;

@Builder
@Getter @Setter @ToString
@AllArgsConstructor
public class CommonResponse {

    private String code;
    private String message;
}
