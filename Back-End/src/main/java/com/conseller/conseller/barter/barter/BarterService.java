package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
import com.conseller.conseller.entity.Barter;

import java.util.List;

public interface BarterService {

    List<BarterResponseDto> getBarterList();
    BarterResponseDto getBarter(Long barterIdx);

    Void addBarter(BarterCreateDto barterCreateDto);



}
