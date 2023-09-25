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

    private Long storeUserDeposit;


}
