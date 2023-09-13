package com.conseller.conseller.sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleListRequest {
    private Integer mainCategory;
    private Integer subCategory;
    private String status;
    private String searchQuery;
    private Integer page;

}
