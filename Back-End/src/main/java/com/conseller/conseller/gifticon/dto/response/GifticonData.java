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
    private String gifticonAllImageName;
    private String gifticonName;
    private String gifticonEndDate;

    @Builder
    public GifticonData(Long gifticonIdx, String gifticonAllImageName, String gifticonName, String gifticonEndDate) {
        this.gifticonIdx = gifticonIdx;
        this.gifticonAllImageName = gifticonAllImageName;
        this.gifticonName = gifticonName;
        this.gifticonEndDate = gifticonEndDate;
    }
}
