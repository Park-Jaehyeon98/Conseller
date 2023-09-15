package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.request.AuctionListRequest;
import com.conseller.conseller.auction.auction.dto.request.ModifyAuctionRequest;
import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.DetailAuctionResponse;
import com.conseller.conseller.entity.Auction;

import java.util.List;

public interface AuctionService {

    public List<Auction> getAuctionList(AuctionListRequest request);

    public void registAuction(RegistAuctionRequest request);

    public DetailAuctionResponse detailAuction(Long auctionIdx);

    public void modifyAuction(Long auctionIdx, ModifyAuctionRequest auctionRequest);

    public void deleteAuction(Long auctionIdx);
}
