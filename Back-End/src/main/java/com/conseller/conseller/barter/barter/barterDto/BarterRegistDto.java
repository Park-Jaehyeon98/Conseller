package com.conseller.conseller.barter.barter.barterDto;

import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.SubCategory;
import com.conseller.conseller.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BarterRegistDto {
    private String barterName;
    private String barterText;
    private LocalDateTime barterEndDate;
    private User barterHost;
    private SubCategory subCategory;
    private SubCategory preferSubCategory;

    @Builder
    public BarterRegistDto(String barterName, String barterText, LocalDateTime barterEndDate, SubCategory subCategory, SubCategory preferSubCategory) {
        this.barterName = barterName;
        this.barterText = barterText;
        this.barterEndDate = barterEndDate;
        this.subCategory = subCategory;
        this.preferSubCategory = preferSubCategory;
    }

    public Barter toEntity(BarterRegistDto barterRegistDto) {
        return Barter.builder()
                .barterName(barterRegistDto.getBarterName())
                .barterText(barterRegistDto.getBarterText())
                .barterEndDate(barterRegistDto.getBarterEndDate())
                .barterHost(barterRegistDto.getBarterHost())
                .subCategory(barterRegistDto.getSubCategory())
                .preferSubCategory(barterRegistDto.getPreferSubCategory())
                .build();
    }
}
