package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
import com.conseller.conseller.entity.Barter;

import java.util.List;

public interface BarterService {

    List<BarterResponseDto> getBarterList();
    BarterResponseDto getBarter(Long barterIdx);
    List<BarterResponseDto> getBarterListByHost(Long userIdx);

    void addBarter(BarterCreateDto barterCreateDto);

    void modifyBarter(Long barterIdx, BarterModifyRequestDto barterModifyRequestDto);

    void deleteBarter(Long barterIdx);

    void exchangeGifticon(Long barterIdx, Long barterRequestIdx);
}
