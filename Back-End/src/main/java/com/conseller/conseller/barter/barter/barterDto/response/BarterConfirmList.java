package com.conseller.conseller.barter.barter.barterDto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BarterConfirmList {
    private String gifticonDataImageName;
    private String gifticonName;
    private String gifticonEndDate;

    @Builder
    public BarterConfirmList(String gifticonDataImageName, String gifticonName, String gifticonEndDate){
        this.gifticonDataImageName = gifticonDataImageName;
        this.gifticonName = gifticonName;
        this.gifticonEndDate = gifticonEndDate;
    }
}
