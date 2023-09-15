package com.conseller.conseller.auction.auction.dto.response;

import com.conseller.conseller.entity.AuctionBid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailAuctionResponse {
    private String auctionText;
    private Long auctionUserIdx;
    private String auctionUserNickname;
    private List<AuctionBid> auctionBidList;
}
