package com.conseller.conseller.gifticon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum GifticonStatus {
    KEEP("보관"),
    AUCTION("경매"),
    STORE("판매"),
    BARTER("물물교환");

    private final String status;
}
