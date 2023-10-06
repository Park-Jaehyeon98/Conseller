package com.conseller.conseller.auction.bid.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BidStatus {
    AWARDED("낙찰"),
    EXPECTED("낙찰 예정"),
    BIDED("입찰"),
    FAILURE("실패");

    private final String status;
}
