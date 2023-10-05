package com.conseller.conseller.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreConfirmResponse {
    private String gifticonDataImageName;

    private String notificationCreatedDate;

    private String giftconName;

    private Integer storePrice;

    private String postContent;

    private String buyUserImageUrl;

    private String buyUserNickname;

    private String buyUserName;

    private Long buyUserIdx;
}
