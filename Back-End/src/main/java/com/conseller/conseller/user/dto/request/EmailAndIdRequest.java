package com.conseller.conseller.user.dto.request;

import lombok.*;

@Builder
@Getter @Setter @ToString
@AllArgsConstructor
public class EmailAndIdRequest {

    private String userEmail;
    private String userId;

}
