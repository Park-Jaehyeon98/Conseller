package com.conseller.conseller.barter.barterRequest.barterRequestService;

import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestRegistDto;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestResponseDto;

import java.util.List;

public interface BarterRequestService {

    List<BarterRequestResponseDto> getBarterRequestList();

    BarterRequestResponseDto getBarterRequest(Long barterRequestIdx);

    List<BarterRequestResponseDto> getBarterRequestListByBarterIdx(Long barterIdx);

    List<BarterRequestResponseDto> getBarterRequestListByRequester(Long userIdx);

    void addBarterRequest(BarterRequestRegistDto barterRequestRegistDto);

    void deleteBarterRequest(Long barterRequestIdx);
}
