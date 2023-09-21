package com.conseller.conseller.inquiry.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistInquiryRequest {
    private Long userIdx;

    private String inquiryName;

    private String inquiryText;

    private LocalDateTime inquiryCreatedDate;

    private String inquiryType;
}
