package com.conseller.conseller.gifticon.dto.response;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ExpiringGifticonResponse {

    private long userIdx;
    private int expiryDay;
    private String gifticonName;
    private int gifticonCnt;
}
