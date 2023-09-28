package com.conseller.conseller.inquiry;

import com.conseller.conseller.entity.Inquiry;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
import com.conseller.conseller.inquiry.dto.mapper.InquiryMapper;
import com.conseller.conseller.inquiry.dto.request.AnswerInquiryRequest;
import com.conseller.conseller.inquiry.dto.request.RegistInquiryRequest;
import com.conseller.conseller.inquiry.dto.response.DetailInquiryResponse;
import com.conseller.conseller.inquiry.dto.response.InquiryItemData;
import com.conseller.conseller.inquiry.dto.response.InquiryListResponse;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryServiceImpl implements InquiryService{
    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;

    @Override
    public void registInquiry(RegistInquiryRequest request) {
        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));

        Inquiry inquiry = InquiryMapper.INSTANCE.registInquiryToInquiry(request, user);

        inquiryRepository.save(inquiry);
    }

    @Override
    @Transactional(readOnly = true)
    public InquiryListResponse getInquiryList() {
        List<Inquiry> inquiryList = inquiryRepository.findAll(Sort.by(Sort.Order.desc("inquiryCreatedDate")));

        List<InquiryItemData> itemData = InquiryMapper.INSTANCE.inquirysToItemDatas(inquiryList);

        InquiryListResponse response = new InquiryListResponse(itemData);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public DetailInquiryResponse detailInquiry(Long inquiryIdx) {
        Inquiry inquiry = inquiryRepository.findById(inquiryIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.INQUIRY_INVALID));

        User user = userRepository.findById(inquiry.getUser().getUserIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));

        DetailInquiryResponse response = InquiryMapper.INSTANCE.entityToDetailInquiryResponse(user, inquiry);

        return response;
    }

    @Override
    public void answerInquiry(Long inquiryIdx, AnswerInquiryRequest request) {
        Inquiry inquiry = inquiryRepository.findById(inquiryIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.INQUIRY_INVALID));

        inquiry.setInquiryAnswer(request.getInquiryAnswer());
        inquiry.setInquiryAnswerDate(LocalDateTime.now());

    }
}
