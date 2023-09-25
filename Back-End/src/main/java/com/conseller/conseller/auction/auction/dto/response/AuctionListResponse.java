package com.conseller.conseller.auction.auction.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuctionListResponse {
    private List<AuctionItemData> items;

    private Long totalElements;

    private Integer totalPages;
}
