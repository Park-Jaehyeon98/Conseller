package com.conseller.conseller.auction.auction.dto.mapper;

import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.AuctionBidItemData;
import com.conseller.conseller.auction.auction.dto.response.AuctionItemData;
import com.conseller.conseller.auction.auction.dto.response.DetailAuctionResponse;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    // RegistAuctionRequest -> Auction 매핑
    @Mapping(source = "user", target = "user")
    @Mapping(source = "gifticon", target = "gifticon")
    Auction registAuctionRequestToAuction(RegistAuctionRequest registAuctionRequest, User user, Gifticon gifticon);

    //User, Auction, AuctionBidList -> DetailAuctionResponse 매핑
    @Mapping(source = "auctionBidList", target = "auctionBidList")
    @Mapping(source = "user.userIdx", target = "auctionUserIdx")
    @Mapping(source = "user.userNickname", target = "auctionUserNickname")
    @Mapping(source = "user.userProfileUrl", target = "auctionUserProfileUrl")
    @Mapping(source = "user.userDeposit", target = "auctionUserDeposit")
    @Mapping(source = "auction.auctionText", target = "postContent")
    DetailAuctionResponse entityToDetailAuctionResponse(User user, Auction auction, List<AuctionBidItemData> auctionBidList);

    //AuctionList -> AuctionItemDataList 매핑
    @Named("A2A")
    default AuctionItemData auctionToItemData(Auction auction){
        AuctionItemData itemData = new AuctionItemData();
        itemData.setAuctionIdx(auction.getAuctionIdx());
        itemData.setGifticonDataImageName(auction.getGifticon().getGifticonDataImageUrl());
        itemData.setGifticonName(auction.getGifticon().getGifticonName());
        itemData.setGifticonEndDate(auction.getGifticon().getGifticonEndDate());
        itemData.setAuctionEndDate(auction.getAuctionEndDate());
        itemData.setPopular("0");
        itemData.setUpperPrice(auction.getUpperPrice());
        itemData.setLowerPrice(auction.getLowerPrice());
        itemData.setAuctionHighestBid(auction.getAuctionHighestBid());

        return itemData;
    }

    @IterableMapping(qualifiedByName = "A2A")
    List<AuctionItemData> auctionsToItemDatas(List<Auction> auctionList);

    //AuctionBidList -> AuctionBidItemDataList 매핑
    @Named("B2B")
    default AuctionBidItemData bidToItemData(AuctionBid auctionBid) {
        AuctionBidItemData itemData = new AuctionBidItemData();

        itemData.setAuctionBidIdx(auctionBid.getAuctionBidIdx());
        itemData.setAuctionBidPrice(auctionBid.getAuctionBidPrice());
        itemData.setAuctionRegistedDate(auctionBid.getAuctionRegistedDate());
        itemData.setAuctionBidStatus(auctionBid.getAuctionBidStatus());
        itemData.setUserIdx(auctionBid.getUser().getUserIdx());
        itemData.setAuctionIdx(auctionBid.getAuction().getAuctionIdx());

        return itemData;
    }

    @IterableMapping(qualifiedByName = "B2B")
    List<AuctionBidItemData> bidsToItemDatas(List<AuctionBid> auctionBidList);

}
