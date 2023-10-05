package com.conseller.conseller.inquiry.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryItemData {
    private Long userIdx;

    private Long inquiryIdx;

    private String inquiryTitle;

    private LocalDateTime inquiryCreatedDate;

    private String inquiryStatus;

    private Integer inquiryType;
}
