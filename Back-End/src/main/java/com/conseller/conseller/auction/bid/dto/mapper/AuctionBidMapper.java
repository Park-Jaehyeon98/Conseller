package com.conseller.conseller.auction.bid.dto.mapper;

import com.conseller.conseller.auction.bid.dto.request.AuctionBidRequest;
import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.AuctionBid;
import com.conseller.conseller.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuctionBidMapper {
    AuctionBidMapper INSTANCE = Mappers.getMapper(AuctionBidMapper.class);

    //User, Auction, requset -> AuctionBid Entity 매핑
    @Mapping(source = "user", target = "user")
    @Mapping(source = "auction", target = "auction")
    AuctionBid registRequestToAuctionBid(User user, Auction auction, AuctionBidRequest request);
}
