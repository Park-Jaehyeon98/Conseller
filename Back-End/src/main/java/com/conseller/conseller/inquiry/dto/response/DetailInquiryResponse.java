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
public class DetailInquiryResponse {
    private Long userIdx;

    private String inquiryTitle;

    private String inquiryContent;

    private String inquiryAnswer;

    private LocalDateTime inquiryAnswerDate;

    private String inquiryStatus;

    private Integer inquiryType;
}
