package com.conseller.conseller.inquiry.dto.mapper;

import com.conseller.conseller.entity.Inquiry;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.inquiry.dto.request.RegistInquiryRequest;
import com.conseller.conseller.inquiry.dto.response.DetailInquiryResponse;
import com.conseller.conseller.inquiry.dto.response.InquiryItemData;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InquiryMapper {
    InquiryMapper INSTANCE = Mappers.getMapper(InquiryMapper.class);

    //RegistInquiryRequest -> Inquiry 매핑
    @Mapping(source = "user", target = "user")
    Inquiry registInquiryToInquiry(RegistInquiryRequest request, User user);

    //User, Inquiry -> DetailInquiryResponse
    @Mapping(source = "user.userIdx", target = "userIdx")
    DetailInquiryResponse entityToDetailInquiryResponse(User user, Inquiry inquiry);

    //InquiryList -> InquiryItemDataList 매핑
    @Named("I2I")
    default InquiryItemData inquiryToItemData(Inquiry inquiry){
        InquiryItemData itemData = new InquiryItemData();

        itemData.setUserIdx(inquiry.getUser().getUserIdx());
        itemData.setInquiryIdx(inquiry.getInquiryIdx());
        itemData.setInquiryName(inquiry.getInquiryName());
        itemData.setInquiryCreatedDate(inquiry.getInquiryCreatedDate());
        itemData.setInquiryStatus(inquiry.getInquiryStatus());
        itemData.setInquiryType(inquiry.getInquiryType());

        return itemData;
    }

    @IterableMapping(qualifiedByName = "I2I")
    List<InquiryItemData> inquirysToItemDatas(List<Inquiry> inquiryList);
}
