package com.conseller.conseller.gifticon.dto.response;

import lombok.*;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GifticonResponse {

    private long gifticonIdx;
    private String gifticonBarcode;
    private String gifticonName;
    private String gifticonStartDate;
    private String gifticonEndDate;
    private String gifticonModifiedDate;
    private String gifticonAllImageUrl;
    private String gifticonDataImageUrl;
    private String gifticonStatus;
    private long userIdx;
    private int subCategoryIdx;
    private int mainCategoryIdx;
}
