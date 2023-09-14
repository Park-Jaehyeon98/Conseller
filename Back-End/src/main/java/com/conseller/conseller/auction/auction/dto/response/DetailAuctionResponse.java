package com.conseller.conseller.auction.auction.dto.response;

import com.conseller.conseller.entity.AuctionBid;

import java.util.List;

public class DetailAuctionResponse {
    private String auctionText;
    private Long auctionUserIdx;
    private String auctionUserNickname;
    private List<AuctionBid> auctionBidList;
}
