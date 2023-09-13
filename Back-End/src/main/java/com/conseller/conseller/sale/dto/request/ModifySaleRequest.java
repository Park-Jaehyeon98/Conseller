package com.conseller.conseller.sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifySaleRequest {
    private LocalDateTime saleEndDate;

    private String saleText;
}
