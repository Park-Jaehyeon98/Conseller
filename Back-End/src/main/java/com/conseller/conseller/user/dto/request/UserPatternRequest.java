package com.conseller.conseller.user.dto.request;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.*;

@ToString
@Getter @Setter @Builder
@AllArgsConstructor
public class UserPartternRequest {

    @NoNull
    private Long userIdx;

    @NotBlank(message = "패턴을 입렫해야 합니다.")
    private String pattern

}