package com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto;

import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.BarterHostItem;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BarterHostItemDto {
    private long barterIdx;
    private GifticonResponse gifticon;

    @Builder
    public BarterHostItemDto(long barterIdx, GifticonResponse gifticon){
        this.barterIdx = barterIdx;
        this.gifticon = gifticon;
    }
}
