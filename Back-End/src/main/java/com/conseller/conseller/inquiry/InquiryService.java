package com.conseller.conseller.inquiry;

import com.conseller.conseller.inquiry.dto.request.AnswerInquiryRequest;
import com.conseller.conseller.inquiry.dto.request.RegistInquiryRequest;
import com.conseller.conseller.inquiry.dto.response.DetailInquiryResponse;
import com.conseller.conseller.inquiry.dto.response.InquiryListResponse;

public interface InquiryService {
    public void registInquiry(RegistInquiryRequest request);

    public InquiryListResponse getInquiryList();

    public DetailInquiryResponse detailInquiry(Long inquiryIdx);

    public void answerInquiry(Long inquiryIdx, AnswerInquiryRequest request);

}
