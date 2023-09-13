package com.conseller.conseller.sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifySaleRequest {
    private Long saleIdx;

    private String saleText;
}
