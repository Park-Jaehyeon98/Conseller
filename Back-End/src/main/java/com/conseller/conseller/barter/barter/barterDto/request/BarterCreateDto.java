package com.conseller.conseller.barter.barter.barterDto.request;

import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.SubCategory;
import com.conseller.conseller.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BarterCreateDto {
    private Integer mainCategory;
    private Integer subCategory;
    private String barterName;
    private String barterText;
    private String barterEndDate;
    private List<Long> selectedItemIndices;
    private Long userIdx;

    @Builder
    public BarterCreateDto(String barterName, String barterText, Integer barterSubCategory, Integer preferSubCategory, List<Long> selectedItemIndices, String barterEndDate, Long userIdx) {
        this.barterName = barterName;
        this.barterText = barterText;
        this.subCategory = barterSubCategory;
        this.mainCategory = barterSubCategory;
        this.selectedItemIndices = selectedItemIndices;
        this.barterEndDate = barterEndDate;
        this.userIdx = userIdx;
    }

    public Barter toEntity(BarterCreateDto barterCreateDto, User host, LocalDateTime endDate, SubCategory subCategory, SubCategory preferCategory) {
        return Barter.builder()
                .barterName(barterCreateDto.getBarterName())
                .barterText(barterCreateDto.getBarterText())
                .barterHost(host)
                .barterEndDate(endDate)
                .subCategory(subCategory)
                .preferSubCategory(preferCategory)
                .build();
    }
}

