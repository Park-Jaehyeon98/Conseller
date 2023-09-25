package com.conseller.conseller.barter.barter.barterDto.request;

import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.SubCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BarterModifyRequestDto {
    private Integer subCategoryIdx;
    private Integer preferSubCategory;
    private String barterName;
    private String barterText;
    private LocalDateTime barterEndDate;

    @Builder
    public Barter toEntity(BarterModifyRequestDto barterModifyRequestDto, SubCategory subCategory, SubCategory preferSubCategory) {
        return Barter.builder()
                .subCategory(subCategory)
                .preferSubCategory(preferSubCategory)
                .barterName(barterModifyRequestDto.getBarterName())
                .barterText(barterModifyRequestDto.getBarterText())
                .barterEndDate(barterModifyRequestDto.getBarterEndDate())
                .build();
    }
}
