package com.conseller.conseller.inquiry.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryType {
    STORE("판매"),
    BARTER("물물교환"),
    AUCTION("경매");

    private final String status;
}
