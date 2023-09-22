package com.conseller.conseller.user.dto.response;

import lombok.*;

@ToString
@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PartialHiddenUserIdResponse {

    private String userEncodeId;

}
