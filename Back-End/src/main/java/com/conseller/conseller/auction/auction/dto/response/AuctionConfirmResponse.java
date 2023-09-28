package com.conseller.conseller.auction.auction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionConfirmResponse {
    private String gifticonDataImageName;

    private String notificationCreatedDate;

    private String giftconName;

    private Integer auctionPrice;

    private String postContent;

    private String buyUserImageUrl;

    private String buyUserNickname;

    private Long buyUserIdx;
}
