package com.conseller.conseller.barter.barter.barterService;

import com.conseller.conseller.barter.barter.barterDto.request.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterFilterDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.response.BarterListResponse;
import com.conseller.conseller.barter.barter.barterDto.response.BarterResponseDto;
import com.conseller.conseller.entity.Barter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BarterService {

    BarterListResponse getBarterList(BarterFilterDto barterFilterDto);

    BarterResponseDto getBarter(Long barterIdx);
    List<BarterResponseDto> getBarterListByHost(Long userIdx);

    Long addBarter(BarterCreateDto barterCreateDto);

    void modifyBarter(Long barterIdx, BarterModifyRequestDto barterModifyRequestDto);

    void deleteBarter(Long barterIdx);

    void exchangeGifticon(Long barterIdx, Long barterRequestIdx);
}
