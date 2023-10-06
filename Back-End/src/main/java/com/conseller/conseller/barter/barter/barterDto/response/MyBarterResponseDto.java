package com.conseller.conseller.barter.barter.barterDto.response;

import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import lombok.*;

import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MyBarterResponseDto {
    private Long barterIdx;
    private String barterName;
    private String barterText;
    private String barterCreatedDate;
    private String barterEndDate;
    private String barterStatus;
    private String subCategory;
    private String preferSubCategory;
    private Long barterHostIdx;
    private Long barterCompleteGuestIdx;
    private List<GifticonResponse> barterHostItems;
}
