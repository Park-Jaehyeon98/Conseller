package com.conseller.conseller.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleListRequest {
    private String mainCategoryContent;
    private String subCategoryContent;
    private String status;
    private String searchQuery;
    private Integer page;

}
