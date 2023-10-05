package com.conseller.conseller.auction.auction.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItemData {
    private Long auctionIdx;
    private String gifticonDataImageName;
    private String gifticonName;
    private String gifticonEndDate;
    private String auctionEndDate;
    private String auctionStatus;
    private Boolean deposit;
    private Integer upperPrice;
    private Integer lowerPrice;
    private Integer auctionHighestBid;
}
