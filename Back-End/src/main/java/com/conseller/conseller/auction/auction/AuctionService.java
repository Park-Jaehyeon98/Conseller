package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.request.AuctionListRequest;
import com.conseller.conseller.auction.auction.dto.request.ModifyAuctionRequest;
import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.*;
import com.conseller.conseller.entity.Auction;

import java.util.List;

public interface AuctionService {

    public AuctionListResponse getAuctionList(AuctionListRequest request);

    public Long registAuction(RegistAuctionRequest request);

    public DetailAuctionResponse detailAuction(Long auctionIdx);

    public void modifyAuction(Long auctionIdx, ModifyAuctionRequest auctionRequest);

    public void deleteAuction(Long auctionIdx);

    public AuctionTradeResponse tradeAuction(Long auctionIdx, Long userIdx);

    public void cancelAuction(Long auctionIdx);

    public void confirmAuction(Long auctionIdx);

    public AuctionConfirmResponse getConfirmAuction(Long auctionIdx);

    public AuctionConfirmBuyResponse getConfirmBuyAuction(Long auctionIdx);

    public List<Auction> getAuctionConfirmList();

    public List<Auction> getAuctionExpiredList();

    public void rejectAuction(Long auctionIdx);
}
