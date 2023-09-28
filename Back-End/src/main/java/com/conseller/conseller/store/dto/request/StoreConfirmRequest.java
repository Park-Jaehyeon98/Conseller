package com.conseller.conseller.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreConfirmRequest {
    private Long storeIdx;

    private Boolean confirm;
}
