package com.conseller.conseller.auction.auction.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuctionStatus {
    IN_PROGRESS("진행 중"),
    IN_TRADE("거래 중"),
    AWARDED("낙찰"),
    TRADED("거래 완료");

    private final String status;
}
