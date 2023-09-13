package com.conseller.conseller.sale.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailSaleResponse {
    private LocalDateTime saleCreatedDate;

    private LocalDateTime saleEndedDate;

    private String saleText;

    private Long saleUserIdx;

    private String saleUserNickname;

}
