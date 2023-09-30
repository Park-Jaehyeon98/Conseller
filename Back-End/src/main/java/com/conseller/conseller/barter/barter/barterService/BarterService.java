package com.conseller.conseller.barter.barter.barterService;

import com.conseller.conseller.barter.barter.barterDto.request.BarterConfirmPageResponseDTO;
import com.conseller.conseller.barter.barter.barterDto.request.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterFilterDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.response.BarterDetailResponseDTO;
import com.conseller.conseller.barter.barter.barterDto.response.BarterResponse;
import com.conseller.conseller.entity.Barter;


import java.util.List;

public interface BarterService {

    BarterResponse getBarterList(BarterFilterDto barterFilterDto);

    BarterDetailResponseDTO getBarter(Long barterIdx, Long userIdx);

    Long addBarter(BarterCreateDto barterCreateDto);

    void modifyBarter(Long barterIdx, BarterModifyRequestDto barterModifyRequestDto);

    void deleteBarter(Long barterIdx);

    void exchangeGifticon(Long barterIdx, Long barterRequestIdx);

    void rejectRequest(Long barterIdx, Long userIdx);

    BarterConfirmPageResponseDTO getBarterConfirmPage(Long barterIdx);

    List<Barter> getExpiredBarterList();
}
