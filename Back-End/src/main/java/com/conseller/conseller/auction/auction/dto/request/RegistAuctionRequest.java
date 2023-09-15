package com.conseller.conseller.auction.auction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistAuctionRequest {
    private Integer upperPrice;
    private Integer lowerPrice;
    private String auctionText;
    private Long gifticonIdx;
    private Long userIdx;
}
