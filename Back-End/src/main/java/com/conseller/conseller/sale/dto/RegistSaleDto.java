package com.conseller.conseller.sale.dto;

import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.entity.Sale;
import com.conseller.conseller.entity.User;
import com.conseller.conseller.sale.dto.request.RegistSaleRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistSaleDto {
    private Integer salePrice;
    private User saleConsumer;
    private Gifticon gifticon;
    private String saleText;

    @Builder
    public RegistSaleDto(RegistSaleRequest request, User user, Gifticon gifticon) {
        this.salePrice = request.getSalePrice();
        this.saleConsumer = user;
        this.gifticon = gifticon;
        this.saleText = request.getSaleText();
    }

    public Sale toEntity(RegistSaleDto registSaleDto) {
        return Sale.builder()
                .salePrice(registSaleDto.getSalePrice())
                .user(registSaleDto.getSaleConsumer())
                .gifticon(registSaleDto.getGifticon())
                .saleText(registSaleDto.getSaleText())
                .build();
    }

}
