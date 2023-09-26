package com.conseller.conseller.barter.barter.barterDto.response;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto.BarterHostItemDto;
import com.conseller.conseller.barter.barter.enums.BarterStatus;
import com.conseller.conseller.entity.SubCategory;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.response.UserInfoResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<BarterHostItemDto> barterHostItemDtoList;

    @Builder
    public BarterResponseDto(Long barterIdx, String barterName, String barterText, String barterCreatedDate, String barterEndDate, String barterStatus, String subCategory, String preferSubCategory, UserInfoResponse barterHost, UserInfoResponse barterCompleteGuest, List<BarterHostItemDto> barterHostItemDtoList) {
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
        this.barterHostItemDtoList = barterHostItemDtoList;
    }
}
