package com.conseller.conseller.auction.auction.dto.mapper;

import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import com.conseller.conseller.auction.auction.dto.response.DetailAuctionResponse;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
    DetailAuctionResponse entityToDetailAuctionResponse(User user, Auction auction, List<AuctionBid> auctionBidList);

}
