package com.conseller.conseller.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StoreStatus {
    IN_PROGRESS("진행 중"),
    IN_TRADE("거래 중"),
    AWARDED("낙찰"),
    TRADED("거래 완료"),
    EXPIRED("만료");

    private final String status;
}
