package com.conseller.conseller.inquiry.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryStatus {
    CHECKING("확인 중"),
    COMPLETED("답변 완료");

    private final String status;
}
