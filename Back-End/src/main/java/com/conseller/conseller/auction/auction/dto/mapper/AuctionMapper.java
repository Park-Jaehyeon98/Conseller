package com.conseller.conseller.auction.auction.dto.mapper;

import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.*;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.utils.DateTimeConverter;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel="spring")
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    // RegistAuctionRequest -> Auction 매핑
    @Mapping(source = "user", target = "user")
    @Mapping(source = "gifticon", target = "gifticon")
    Auction registAuctionRequestToAuction(RegistAuctionRequest registAuctionRequest, User user, Gifticon gifticon);

    //User, Auction, AuctionBidList -> DetailAuctionResponse 매핑
    default DetailAuctionResponse entityToDetailAuctionResponse(Auction auction, List<AuctionBidItemData> auctionBidList) {
        DetailAuctionResponse response = new DetailAuctionResponse();

        response.setAuctionBidList(auctionBidList);
        response.setAuctionIdx(auction.getAuctionIdx());
        response.setAuctionUserIdx(auction.getUser().getUserIdx());
        response.setAuctionUserDeposit(auction.getUser().getUserDeposit());
        response.setAuctionUserNickname(auction.getUser().getUserNickname());
        response.setDeposit(false);
        response.setAuctionEndDate(DateTimeConverter.getInstance().convertString(auction.getAuctionEndDate()));
        response.setAuctionHighestBid(auction.getAuctionHighestBid());
        response.setAuctionUserProfileUrl(auction.getUser().getUserProfileUrl());
        response.setGifticonDataImageName(auction.getGifticon().getGifticonDataImageUrl());
        response.setGifticonName(auction.getGifticon().getGifticonName());
        response.setGifticonEndDate(DateTimeConverter.getInstance().convertString(auction.getGifticon().getGifticonEndDate()));
        response.setPostContent(auction.getAuctionText());
        response.setLowerPrice(auction.getLowerPrice());
        response.setUpperPrice(auction.getUpperPrice());

        return response;
    }

    //AuctionList -> AuctionItemDataList 매핑
    @Named("A2A")
    default AuctionItemData auctionToItemData(Auction auction){
        AuctionItemData itemData = new AuctionItemData();
        itemData.setAuctionIdx(auction.getAuctionIdx());
        itemData.setGifticonDataImageName(auction.getGifticon().getGifticonDataImageUrl());
        itemData.setGifticonName(auction.getGifticon().getGifticonName());
        itemData.setGifticonEndDate(DateTimeConverter.getInstance().convertString(auction.getGifticon().getGifticonEndDate()));
        itemData.setAuctionEndDate(DateTimeConverter.getInstance().convertString(auction.getAuctionEndDate()));
        itemData.setDeposit(false);
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
        itemData.setAuctionRegistedDate(DateTimeConverter.getInstance().convertString(auctionBid.getAuctionRegistedDate()));
        itemData.setAuctionBidStatus(auctionBid.getAuctionBidStatus());
        itemData.setUserIdx(auctionBid.getUser().getUserIdx());
        itemData.setAuctionIdx(auctionBid.getAuction().getAuctionIdx());

        return itemData;
    }

    @IterableMapping(qualifiedByName = "B2B")
    List<AuctionBidItemData> bidsToItemDatas(List<AuctionBid> auctionBidList);

    // auction -> auctionConfirmResponse 매핑
    default AuctionConfirmResponse auctionToConfirm(Auction auction) {
        AuctionConfirmResponse response = new AuctionConfirmResponse();

        response.setGifticonDataImageName(auction.getGifticon().getGifticonDataImageUrl());
        response.setNotificationCreatedDate(DateTimeConverter.getInstance().convertString(LocalDateTime.now()));
        response.setGiftconName(auction.getGifticon().getGifticonName());
        response.setAuctionPrice(auction.getAuctionHighestBid());
        response.setPostContent(auction.getAuctionText());
        response.setBuyUserImageUrl(auction.getHighestBidUser().getUserProfileUrl());
        response.setBuyUserNickname(auction.getHighestBidUser().getUserNickname());
        response.setBuyUserIdx(auction.getHighestBidUser().getUserIdx());

        return response;
    }

    // auction -> auctionConfirmBuyResponse 매핑
    default AuctionConfirmBuyResponse auctionToConfirmBuy(Auction auction) {
        AuctionConfirmBuyResponse response = new AuctionConfirmBuyResponse();

        response.setGifticonDataImageName(auction.getGifticon().getGifticonDataImageUrl());
        response.setNotificationCreatedDate(DateTimeConverter.getInstance().convertString(LocalDateTime.now()));
        response.setGiftconName(auction.getGifticon().getGifticonName());
        response.setAuctionPrice(auction.getAuctionHighestBid());
        response.setPostContent(auction.getAuctionText());
        response.setUserAccount(auction.getUser().getUserAccount());
        response.setUserAccountBank(auction.getUser().getUserAccountBank());
        response.setBuyUserImageUrl(auction.getHighestBidUser().getUserProfileUrl());
        response.setBuyUserNickname(auction.getHighestBidUser().getUserNickname());
        response.setBuyUserIdx(auction.getHighestBidUser().getUserIdx());

        return response;
    }
}
