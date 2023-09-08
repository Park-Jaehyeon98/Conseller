package com.conseller.conseller.user;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public enum UserStatus {
    RESTRICTED("제한"),
    ACTIVE("정상");

    private String status;

    UserStatus(String status) {}
}
