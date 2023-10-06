package com.conseller.conseller.auction.auction.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuctionPopularResponse {
    private List<AuctionItemData> items;
}
