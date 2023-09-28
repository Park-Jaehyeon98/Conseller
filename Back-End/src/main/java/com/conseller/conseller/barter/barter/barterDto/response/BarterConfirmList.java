package com.conseller.conseller.barter.barter.barterDto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BarterConfirmList {
    private String giftconDataImageName;
    private String gifticonName;
    private String gifticonEndDate;

    @Builder
    public BarterConfirmList(String giftconDataImageName, String gifticonName, String gifticonEndDate){
        this.giftconDataImageName = giftconDataImageName;
        this.gifticonName = gifticonName;
        this.gifticonEndDate = gifticonEndDate;
    }
}
