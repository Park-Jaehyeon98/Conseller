package com.conseller.conseller.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailStoreResponse {
    private String postContent;

    private Long storeUserIdx;

    private String storeUserNickname;

    private String storeUserProfileUrl;

    private Integer storeUserDeposit;

    private Long storeIdx;

    private String gifticonDataImageName;

    private String gifticonName;

    private String gifticonEndDate;

    private String storeEndDate;

    private Boolean deposit;

    private Integer storePrice;

}
