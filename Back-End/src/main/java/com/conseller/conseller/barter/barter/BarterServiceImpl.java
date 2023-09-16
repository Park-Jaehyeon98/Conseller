package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemService;
import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.BarterRegistDto;
import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BarterServiceImpl implements BarterService{

    private final BarterRepository barterRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final BarterHostItemService barterHostItemService;
    @Override
    public List<BarterResponseDto> getBarterList() {
        List<Barter> barterList = barterRepository.findAll();
        List<BarterResponseDto> barterResponseDtoList= new ArrayList<>();

        for(Barter barter : barterList) {
            BarterResponseDto barterResponseDto = barter.toBarterResponseDto(barter);
            barterResponseDtoList.add(barterResponseDto);
        }
        return barterResponseDtoList;
    }

    @Override
    public BarterResponseDto getBarter(Long barterIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx).orElseThrow(() -> new RuntimeException());
        BarterResponseDto barterResponseDto = barter.toBarterResponseDto(barter);

        return barterResponseDto;
    }

    @Override
    public List<BarterResponseDto> getBarterListByHost(Long userIdx) {
        List<Barter> barterList = barterRepository.findByHostIdx(userIdx);
        List<BarterResponseDto> barterResponseDtoList = new ArrayList<>();
        for(Barter barter : barterList) {
            barterResponseDtoList.add(barter.toBarterResponseDto(barter));
        }
        return barterResponseDtoList;
    }

    @Override
    public void addBarter(BarterCreateDto barterCreateDto) {
        SubCategory subCategory = subCategoryRepository.findById(barterCreateDto.getBarterSubCategory()).orElseThrow(() -> new RuntimeException());
        SubCategory preferSubCategory = subCategoryRepository.findById(barterCreateDto.getPreferBarterSubCategory()).orElseThrow(() -> new RuntimeException());

        BarterRegistDto barterRegistDto = new BarterRegistDto(barterCreateDto, subCategory, preferSubCategory);

        Barter barter = barterRegistDto.toEntity(barterRegistDto);
        barterRepository.save(barter);

        barterHostItemService.addBarterHostItem(barterCreateDto.getSelectedItemIndices(), barter);
    }

    @Override
    @Transactional
    public void modifyBarter(Long barterIdx, BarterModifyRequestDto barterModifyRequestDto) {
        SubCategory preferSubCategory = subCategoryRepository.findById(barterModifyRequestDto.getSubCategoryIdx())
                .orElseThrow(() -> new RuntimeException());

        barterModifyRequestDto.setPreferSubCategory(preferSubCategory);

        Barter barter = barterRepository.findByBarterIdx(barterIdx).orElseThrow(() -> new RuntimeException());

        barter.modifyBarter(barterModifyRequestDto);
    }

    @Override
    public void deleteBarter(Long barterIdx) {
        barterRepository.deleteById(barterIdx);
    }


}
