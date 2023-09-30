package com.conseller.conseller.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreItemData {
    private Long storeIdx;

    private String gifticonDataImageName;

    private String gifticonName;

    private String gifticonEndDate;

    private String storeEndDate;

    private String storeStatus;

    private Boolean deposit;

    private Integer storePrice;

}
