package com.conseller.conseller.gifticon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GifticonData {
    private Long gifticonIdx;
    private String gifticonImageName;
    private String gifticonName;
    private String gifticonEndDate;

    @Builder
    public GifticonData(Long gifticonIdx, String gifticonImageName, String gifticonName, String gifticonEndDate) {
        this.gifticonIdx = gifticonIdx;
        this.gifticonImageName = gifticonImageName;
        this.gifticonName = gifticonName;
        this.gifticonEndDate = gifticonEndDate;
    }
}
