package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemService;
import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.BarterRegistDto;
import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
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
    private final SubCategoryRepository subCategoryRepository;
    private final BarterHostItemService barterHostItemService;
    @Override
    public List<BarterResponseDto> getBarterList() {
        return null;
    }

    @Override
    public BarterResponseDto getBarter(Long barterIdx) {
        return null;
    }

    @Override
    public Void addBarter(BarterCreateDto barterCreateDto) {
        SubCategory subCategory = subCategoryRepository.findById(barterCreateDto.getBarterSubCategory()).orElseThrow(() -> new RuntimeException());
        SubCategory preferSubCategory = subCategoryRepository.findById(barterCreateDto.getPreferBarterSubCategory()).orElseThrow(() -> new RuntimeException());

        BarterRegistDto barterRegistDto = new BarterRegistDto(barterCreateDto, subCategory, preferSubCategory);

        Barter barter = barterRegistDto.toEntity(barterRegistDto);
        barterRepository.save(barter);

        barterHostItemService.addBarterHostItem(barterCreateDto.getSelectedItemIndices(), barter);

        return null;
    }
}
