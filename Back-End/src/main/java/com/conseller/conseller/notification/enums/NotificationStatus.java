package com.conseller.conseller.notification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationStatus {
    AUCTION("경매"),
    STORE("스토어"),
    BARTER("물물교환"),
    GIFTICON("기프티콘 기간");

    private final String status;
}
