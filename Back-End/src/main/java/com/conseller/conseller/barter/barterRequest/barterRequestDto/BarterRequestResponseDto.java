package com.conseller.conseller.barter.barterRequest.barterRequestDto;

import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
import com.conseller.conseller.barter.barterRequest.enums.RequestStatus;
import com.conseller.conseller.entity.User;
import lombok.Builder;

public class BarterRequestResponseDto {

    private Long barterRequestIdx;

    private String barterRequestStatus;

    private BarterResponseDto barterResponse;
    private User user;

    @Builder
    public BarterRequestResponseDto(Long barterRequestIdx, RequestStatus barterRequestStatus, BarterResponseDto barterResponse, User user){
        this.barterRequestIdx = barterRequestIdx;
        this.barterRequestStatus = barterRequestStatus.getStatus();
        this.barterResponse = barterResponse;
        this.user = user;
    }
}
