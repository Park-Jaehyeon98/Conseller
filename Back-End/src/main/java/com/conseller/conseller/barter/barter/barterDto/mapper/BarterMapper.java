package com.conseller.conseller.barter.barter.barterDto.mapper;

import com.conseller.conseller.barter.barter.barterDto.request.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.response.BarterDetailResponseDTO;
import com.conseller.conseller.barter.barter.barterDto.response.BarterItemData;
import com.conseller.conseller.barter.barter.barterDto.response.MyBarterResponseDto;
import com.conseller.conseller.entity.*;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.utils.DateTimeConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BarterMapper {

    BarterMapper INSTANCE = Mappers.getMapper(BarterMapper.class);

    //BarterCreateDto -> Barter 매핑
    @Mapping(source = "user", target = "barterHost")
    @Mapping(source = "endDate", target = "barterEndDate")
    @Mapping(source = "subCategory", target = "subCategory")
    @Mapping(source = "preferCategory", target = "preferSubCategory")
    Barter registBarterCreateToBarter(BarterCreateDto barterCreateDto, User user, LocalDateTime endDate, SubCategory subCategory, SubCategory preferCategory);


    default BarterItemData toBarterItemData(Barter barter) {
        List<BarterHostItem> barterHostItemList = barter.getBarterHostItemList();
        List<Gifticon> gifticonList = new ArrayList<>();
        Gifticon gifticon = null;
        for(BarterHostItem gift : barterHostItemList) {
            if(gift.getGifticon().getSubCategory() == barter.getSubCategory()) {
                gifticon = gift.getGifticon();
                break;
            }
        }
        Boolean deposit = false;
        if(barter.getBarterHost().getUserDeposit() > 0) {
            deposit = true;
        }

        String preferSubCategory = barter.getPreferSubCategory().getSubCategoryContent();

        if(barter.getPreferSubCategory().getSubCategoryIdx() > 10) {
            preferSubCategory = barter.getPreferSubCategory().getMainCategory().getMainCategoryContent();
        }

        BarterItemData barterItemData = new BarterItemData();
        barterItemData.setBarterIdx(barter.getBarterIdx());
        barterItemData.setGifticonDataImageName(gifticon.getGifticonDataImageUrl());
        barterItemData.setGifticonName(gifticon.getGifticonName());
        barterItemData.setGifticonEndDate(DateTimeConverter.getInstance().convertString(gifticon.getGifticonEndDate()));
        barterItemData.setBarterEndDate(DateTimeConverter.getInstance().convertString(barter.getBarterEndDate()));
        barterItemData.setDeposit(deposit);
        barterItemData.setPreper(preferSubCategory);
        barterItemData.setBarterName(barter.getBarterName());

        return barterItemData;
    }

    //Barter -> MyBarterResponseDto 매핑
    default MyBarterResponseDto toMybarterResponseDto(Barter barter) {
        MyBarterResponseDto.MyBarterResponseDtoBuilder barterResponseDto = MyBarterResponseDto.builder()
                .barterIdx(barter.getBarterIdx())
                .barterName(barter.getBarterName())
                .barterText(barter.getBarterText())
                .barterStatus(barter.getBarterStatus())
                .barterCreatedDate(DateTimeConverter.getInstance().convertString(barter.getBarterCreatedDate()))
                .barterEndDate(DateTimeConverter.getInstance().convertString(barter.getBarterEndDate()))
                .barterHostIdx(barter.getBarterHost().getUserIdx())
                .subCategory(String.valueOf(barter.getSubCategory().getSubCategoryIdx()));

        List<GifticonResponse> gifticonResponses = barter.getBarterHostItemList().stream()
                .map(item -> item.getGifticon().toResponseDto())
                .collect(Collectors.toList());

        barterResponseDto.barterHostItems(gifticonResponses);

        if (barter.getBarterCompleteGuest() != null) {
            barterResponseDto.barterCompleteGuestIdx(barter.getBarterCompleteGuest().getUserIdx());
        }

        return barterResponseDto.build();
    }
}

