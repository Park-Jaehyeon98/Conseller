package com.conseller.conseller.barter.request.enums;

import lombok.Getter;

@Getter
public enum RequestStatus {
    WAIT("요청"),
    ACCEPTED("수락"),
    REJECTED("거절");

    private final String status;
    RequestStatus(String status) { this.status = status; }
}
