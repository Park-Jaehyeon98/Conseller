package com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto;

import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.BarterHostItem;
import com.conseller.conseller.entity.Gifticon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BarterHostItemDto {
    private Barter barter;
    private Gifticon gifticon;

    @Builder
    public BarterHostItemDto(Barter barter, Gifticon gifticon){
        this.barter = barter;
        this.gifticon = gifticon;
    }

    public BarterHostItem toEntity(BarterHostItemDto barterHostItemDto){
        return BarterHostItem.builder()
                .barter(barterHostItemDto.getBarter())
                .gifticon(barterHostItemDto.getGifticon())
                .build();
    }
}
