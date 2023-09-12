package com.conseller.conseller.sale.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponse {
    private Boolean success;
    private String message;
}
