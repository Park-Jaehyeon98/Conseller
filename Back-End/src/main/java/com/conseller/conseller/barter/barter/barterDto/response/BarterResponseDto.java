package com.conseller.conseller.barter.barter.barterDto.response;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto.BarterHostItemDto;

import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestResponseDto;
import com.conseller.conseller.user.dto.response.UserInfoResponse;
import lombok.Builder;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BarterResponseDto {
    private Long barterIdx;
    private String barterName;
    private String barterText;
    private String barterCreatedDate;
    private String barterEndDate;
    private String barterStatus;
    private String subCategory;
    private String preferSubCategory;
    private UserInfoResponse barterHost;
    private UserInfoResponse barterCompleteGuest;
    private List<BarterRequestResponseDto> barterRequestResponseDtoList;
    private List<BarterHostItemDto> barterHostItemDtoList;

    @Builder
    public BarterResponseDto(Long barterIdx, String barterName, String barterText, String barterCreatedDate, String barterEndDate, String barterStatus, String subCategory, String preferSubCategory, UserInfoResponse barterHost, UserInfoResponse barterCompleteGuest,List<BarterRequestResponseDto> barterRequestResponseDtoList, List<BarterHostItemDto> barterHostItemDtoList) {
        this.barterIdx = barterIdx;
        this.barterName = barterName;
        this.barterText = barterText;
        this.barterCreatedDate = barterCreatedDate;
        this.barterEndDate = barterEndDate;
        this.barterStatus = barterStatus;
        this.subCategory = subCategory;
        this.preferSubCategory = preferSubCategory;
        this.barterHost = barterHost;
        this.barterCompleteGuest = barterCompleteGuest;
        this.barterRequestResponseDtoList = barterRequestResponseDtoList;
        this.barterHostItemDtoList = barterHostItemDtoList;
    }
}
