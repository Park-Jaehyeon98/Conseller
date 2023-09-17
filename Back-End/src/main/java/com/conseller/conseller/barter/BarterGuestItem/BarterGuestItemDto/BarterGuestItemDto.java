package com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemDto;

import com.conseller.conseller.entity.BarterGuestItem;
import com.conseller.conseller.entity.BarterRequest;
import com.conseller.conseller.entity.Gifticon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BarterGuestItemDto {
    private BarterRequest barterRequest;
    private Gifticon gifticon;

    @Builder
    public BarterGuestItemDto(BarterRequest barterRequest, Gifticon gifticon) {
        this.barterRequest = barterRequest;
        this.gifticon = gifticon;
    }

    public BarterGuestItem toEntity(BarterRequest barterRequest, Gifticon gifticon) {
        return BarterGuestItem.builder()
                .barterRequest(barterRequest)
                .gifticon(gifticon)
                .build();
    }
}
