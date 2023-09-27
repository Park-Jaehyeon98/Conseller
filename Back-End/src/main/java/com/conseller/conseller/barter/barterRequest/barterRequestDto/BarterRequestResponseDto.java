package com.conseller.conseller.barter.barterRequest.barterRequestDto;

import com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemDto.BarterGuestItemDto;
import com.conseller.conseller.barter.barter.barterDto.response.BarterResponseDto;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.user.dto.response.UserInfoResponse;
import lombok.Builder;

import java.util.List;

public class BarterRequestResponseDto {

    private Long barterRequestIdx;
    private String barterRequestStatus;
    private Long barterIdx;
    private UserInfoResponse user;
    private List<BarterGuestItemDto> barterGuestItemDtoList;

    @Builder
    public BarterRequestResponseDto(Long barterRequestIdx, String barterRequestStatus, Long barterIdx, UserInfoResponse user, List<BarterGuestItemDto> barterGuestItemDtoList){
        this.barterRequestIdx = barterRequestIdx;
        this.barterRequestStatus = barterRequestStatus;
        this.barterIdx = barterIdx;
        this.user = user;
        this.barterGuestItemDtoList = barterGuestItemDtoList;
    }
}
