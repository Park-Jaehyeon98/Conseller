package com.conseller.conseller.sale.dto.response;

import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailSaleResponse {
    private LocalDateTime saleCreatedDate;

    private LocalDateTime saleEndedDate;

    private String saleText;

    private Long saleUserIdx;

    private String saleUserNickname;

    public DetailSaleResponse(User user, Sale sale) {
        this.saleCreatedDate = sale.getSaleCreatedDate();
        this.saleEndedDate = sale.getSaleEndedDate();
        this.saleText = sale.getSaleText();
        this.saleUserIdx = user.getUserIdx();
        this.saleUserNickname = user.getUserNickname();
    }

}
