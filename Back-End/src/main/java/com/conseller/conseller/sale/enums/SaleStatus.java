package com.conseller.conseller.sale.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SaleStatus {
    IN_PROGRESS("진행 중"),
    IN_TRADE("거래 중"),
    AWARDED("낙찰");

    private final String status;
}
