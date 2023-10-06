package com.conseller.conseller.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistStoreRequest {
    private Integer storePrice;

    private String storeText;

    private Long gifticonIdx;

    private Long userIdx;
}
