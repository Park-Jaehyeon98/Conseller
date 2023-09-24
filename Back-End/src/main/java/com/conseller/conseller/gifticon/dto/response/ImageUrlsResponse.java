package com.conseller.conseller.gifticon.dto.response;

import lombok.*;

@Builder
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ImageUrlsResponse {

    private String gifticonAllImageUrl;
    private String gifticonDataImageUrl;
}
