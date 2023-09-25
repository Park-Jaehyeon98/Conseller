package com.conseller.conseller.auction.auction.dto.response;

import com.conseller.conseller.entity.Auction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionListResponse {
    private Page<Auction> items;
}
