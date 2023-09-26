package com.conseller.conseller.auction.bid.dto.response;

import com.conseller.conseller.auction.auction.dto.response.AuctionItemData;
import lombok.*;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuctionBidResponse {

    private long auctionBidIdx;

    private int auctionBidPrice;

    private String auctionRegistedDate;

    private String auctionBidStatus;

    private AuctionItemData auctionItemData;
}
