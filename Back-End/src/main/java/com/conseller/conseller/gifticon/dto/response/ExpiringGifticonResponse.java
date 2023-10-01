package com.conseller.conseller.gifticon.dto.response;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ExpiringGifticonResponse {

    private Long userIdx;
    private Integer expiryDay;
    private String gifticonName;
    private Long gifticonCnt;
}
