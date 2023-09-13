package com.conseller.conseller.auction.auction.dto.request;

import com.conseller.conseller.entity.Auction;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistAuctionRequest {
    private Integer upperLimit;
    private Integer lowerLimit;
    private String auctionText;
    private Long gifticonIdx;
    private Gifticon gifticon;
    private Long userIdx;
    private User user;

    public Auction toEntity(RegistAuctionRequest request) {
        return Auction.builder()
                .upperPrice(request.getUpperLimit())
                .lowerPrice(request.getLowerLimit())
                .auctionText(request.getAuctionText())
                .gifticon(request.getGifticon())
                .user(request.getUser())
                .build();
    }

}
