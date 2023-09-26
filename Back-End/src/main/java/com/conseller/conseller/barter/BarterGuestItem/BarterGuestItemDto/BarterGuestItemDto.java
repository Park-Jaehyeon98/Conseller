package com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemDto;

import com.conseller.conseller.entity.BarterGuestItem;
import com.conseller.conseller.entity.BarterRequest;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BarterGuestItemDto {
    private long barterRequestIdx;
    private GifticonResponse gifticon;

    @Builder
    public BarterGuestItemDto(long barterRequestIdx, GifticonResponse gifticon) {
        this.barterRequestIdx = barterRequestIdx;
        this.gifticon = gifticon;
    }
}
