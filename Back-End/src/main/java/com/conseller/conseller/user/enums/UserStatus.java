package com.conseller.conseller.user.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
public enum UserStatus {
    RESTRICTED("제한"),
    ACTIVE("정상");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

}
