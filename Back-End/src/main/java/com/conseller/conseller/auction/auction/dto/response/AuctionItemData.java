package com.conseller.conseller.auction.auction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItemData {
    private Long auctionIdx;
    private String gifticonDataImageName;
    private String gifticonName;
    private LocalDateTime gifticonEndDate;
    private LocalDateTime auctionEndDate;
    private Boolean Deposit;
    private Integer upperPrice;
    private Integer lowerPrice;
    private Integer auctionHighestBid;
}
