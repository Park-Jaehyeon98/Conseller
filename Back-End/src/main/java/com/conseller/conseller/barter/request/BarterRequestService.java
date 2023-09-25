package com.conseller.conseller.barter.request;

import com.conseller.conseller.barter.request.barterRequestDto.BarterRequestRegistDto;
import com.conseller.conseller.barter.request.barterRequestDto.BarterRequestResponseDto;

import java.util.List;

public interface BarterRequestService {

    List<BarterRequestResponseDto> getBarterRequestList();

    BarterRequestResponseDto getBarterRequest(Long barterRequestIdx);

    List<BarterRequestResponseDto> getBarterRequestListByBarterIdx(Long barterIdx);

    List<BarterRequestResponseDto> getBarterRequestListByRequester(Long userIdx);

    void addBarterRequest(BarterRequestRegistDto barterRequestRegistDto);

    void deleteBarterRequest(Long barterRequestIdx);
}
