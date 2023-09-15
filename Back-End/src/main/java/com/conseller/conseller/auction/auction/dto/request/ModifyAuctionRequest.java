package com.conseller.conseller.auction.auction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyAuctionRequest {
    private LocalDateTime auctionEndDate;
    private String auctionText;
}
