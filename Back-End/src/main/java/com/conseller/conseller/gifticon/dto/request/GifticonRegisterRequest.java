package com.conseller.conseller.gifticon.dto.request;

import lombok.*;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GifticonRegisterRequest {

    private String gifticonBarcode;
    private String gifticonName;
    private String gifticonEndDate;
    private int subCategory;
    private int mainCategory;

}
