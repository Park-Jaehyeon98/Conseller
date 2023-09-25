package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.request.AuctionListRequest;
import com.conseller.conseller.auction.auction.dto.request.ModifyAuctionRequest;
import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.AuctionListResponse;
import com.conseller.conseller.auction.auction.dto.response.AuctionTradeResponse;
import com.conseller.conseller.auction.auction.dto.response.DetailAuctionResponse;

public interface AuctionService {

    public AuctionListResponse getAuctionList(AuctionListRequest request);

    public void registAuction(RegistAuctionRequest request);

    public DetailAuctionResponse detailAuction(Long auctionIdx);

    public void modifyAuction(Long auctionIdx, ModifyAuctionRequest auctionRequest);

    public void deleteAuction(Long auctionIdx);

    public AuctionTradeResponse tradeAuction(Long auctionIdx, Integer index);

    public void cancelAuction(Long auctionIdx);

    public void confirmAuction(Long auctionIdx);
}
