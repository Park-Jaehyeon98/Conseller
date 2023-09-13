package com.conseller.conseller.sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistSaleRequest {
    private Integer salePrice;

    private String saleText;

    private Long gifticonIdx;

    private Long userIdx;


}
