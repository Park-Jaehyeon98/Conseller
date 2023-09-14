package com.conseller.conseller.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailStoreResponse {
    private LocalDateTime storeCreatedDate;

    private LocalDateTime storeEndDate;

    private String storeText;

    private Long storeUserIdx;

    private String storeUserNickname;

}
