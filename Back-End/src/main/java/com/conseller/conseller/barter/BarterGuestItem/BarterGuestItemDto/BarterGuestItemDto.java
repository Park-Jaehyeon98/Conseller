package com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemDto;

import com.conseller.conseller.entity.BarterGuestItem;
import com.conseller.conseller.entity.BarterRequest;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.utils.DateTimeConverter;
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
    public BarterGuestItemDto(long barterReuestIdx, GifticonResponse gifticon) {
        this.barterRequestIdx = barterReuestIdx;
        this.gifticon = gifticon;
    }
}
