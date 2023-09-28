package com.conseller.conseller.user.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserPatternRequest {

    @NotNull
    private Long userIdx;

    @NotBlank(message = "패턴을 입렫해야 합니다.")
    private String pattern;
}
