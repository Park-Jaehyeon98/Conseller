package com.conseller.conseller.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreItemData {
    private Long storeIdx;
    private String gifticonDataImageName;
    private String gifticonName;
    private LocalDateTime gifticonEndDate;
    private LocalDateTime storeEndDate;
    private String popular;
    private Integer storePrice;
}
