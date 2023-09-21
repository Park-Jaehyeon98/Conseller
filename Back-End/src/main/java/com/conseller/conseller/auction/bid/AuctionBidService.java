package com.conseller.conseller.auction.bid;

import com.conseller.conseller.auction.bid.dto.request.AuctionBidRequest;

public interface AuctionBidService {
    public void registAuctionBid(Long auctionIdx, AuctionBidRequest request);

    public void deleteAuctionBid(Long auctionBidIdx);
}
