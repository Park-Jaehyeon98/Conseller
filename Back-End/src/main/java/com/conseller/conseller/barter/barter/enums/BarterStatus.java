package com.conseller.conseller.barter.barter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BarterStatus {
    EXCHANGEABLE("교환 가능"),
    SUGGESTED("제안"),
    EXCHANGED("교환 완료"),
    EXPIRED("만료"),
    CANCEL("취소");

    private final String status;
}
