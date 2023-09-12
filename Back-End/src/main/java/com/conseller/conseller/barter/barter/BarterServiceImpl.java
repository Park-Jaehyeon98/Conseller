package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.BarterRegistDto;
import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BarterServiceImpl implements BarterService{

    private final BarterRepository barterRepository;

    @Override
    public List<BarterResponseDto> getBarterList() {
        return null;
    }

    @Override
    public Barter getBarter(Long barterIdx) {
        return null;
    }

    @Override
    public Barter addBarter(BarterCreateDto barterCreateDto) {
//        SubCategory subCategory =
//        BarterRegistDto barterRegistDto = new BarterRegistDto();
//
//        Barter barter = BarterCreateDto.toEntity(BarterCreateDto barterCreateDto);

        return null;
    }
}
