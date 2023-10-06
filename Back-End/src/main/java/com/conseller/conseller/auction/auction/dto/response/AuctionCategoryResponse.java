package com.conseller.conseller.auction.auction.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuctionCategoryResponse {
    private List<Integer> items;
}
