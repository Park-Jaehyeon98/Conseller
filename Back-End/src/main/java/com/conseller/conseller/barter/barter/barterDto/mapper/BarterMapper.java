package com.conseller.conseller.barter.barter.barterDto.mapper;

import com.conseller.conseller.barter.barter.barterDto.request.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.response.MyBarterResponseDto;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.SubCategory;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.utils.DateTimeConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
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

//    @Mapping()
//    BarterResponseDto entityToBarterResponseDto(Barter barter)

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

