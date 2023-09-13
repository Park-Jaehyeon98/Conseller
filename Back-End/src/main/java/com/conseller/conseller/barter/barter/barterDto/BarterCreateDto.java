package com.conseller.conseller.barter.barter.barterDto;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto.BarterHostItemDto;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.BarterHostItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BarterCreateDto {
    private String barterName;
    private String barterText;
    private Integer barterSubCategory;
    private Integer preferBarterSubCategory;
    private List<Long> selectedItemIndices;
    private LocalDateTime barterEndDate;
    private Long userIdx;
}
