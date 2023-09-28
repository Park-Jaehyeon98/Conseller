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
    private Integer mainCategoryIdx;
    private Integer subCategory;
    private String barterName;
    private String barterText;
    private String barterEndDate;
}
