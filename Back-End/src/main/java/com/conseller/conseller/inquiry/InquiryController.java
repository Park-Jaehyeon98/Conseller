package com.conseller.conseller.inquiry;

import com.conseller.conseller.inquiry.dto.request.AnswerInquiryRequest;
import com.conseller.conseller.inquiry.dto.request.RegistInquiryRequest;
import com.conseller.conseller.inquiry.dto.response.DetailInquiryResponse;
import com.conseller.conseller.inquiry.dto.response.InquiryListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {
    private final InquiryService inquiryService;

    // 문의 등록
    @PostMapping
    public ResponseEntity<Object> registInquiry(@RequestBody RegistInquiryRequest request) {
        inquiryService.registInquiry(request);

        return ResponseEntity.ok()
                .build();
    }

    // 문의 목록
    @GetMapping
    public ResponseEntity<InquiryListResponse> getInquiryList() {
        InquiryListResponse response = inquiryService.getInquiryList();

        return ResponseEntity.ok()
                .body(response);
    }

    // 문의 상세보기
    @GetMapping("/{inquiry_idx}")
    public ResponseEntity<DetailInquiryResponse> detailInquiry(@PathVariable("inquiry_idx") Long inquiryIdx) {
        DetailInquiryResponse response = inquiryService.detailInquiry(inquiryIdx);

        return ResponseEntity.ok()
                .body(response);
    }


    // 문의 답변
    @PatchMapping("/{inquiry_idx}")
    public ResponseEntity<Object> answerInquiry(@PathVariable("inquiry_idx") Long inquiryIdx, @RequestBody AnswerInquiryRequest request) {
        inquiryService.answerInquiry(inquiryIdx, request);

        return ResponseEntity.ok()
                .build();
    }
}
