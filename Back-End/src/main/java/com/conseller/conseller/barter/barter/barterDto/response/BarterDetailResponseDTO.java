package com.conseller.conseller.barter.barter.barterDto.response;

import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BarterDetailResponseDTO {
    private String barterName;
    private String barterText;
    private Long barterUserIdx;
    private String barterUserProfileUrl;
    private Long barterUserDeposit;
    private String barterUserNickname;
    private List<GifticonResponse> barterGifticonList;

    @Builder
    public BarterDetailResponseDTO(String barterName, String barterText, Long barterUserIdx, String barterUserProfileUrl, Long barterUserDeposit, String barterUserNickname, List<GifticonResponse> barterGifticonList) {
        this.barterName = barterName;
        this.barterText = barterText;
        this.barterUserIdx = barterUserIdx;
        this.barterUserProfileUrl = barterUserProfileUrl;
        this.barterUserDeposit = barterUserDeposit;
        this.barterUserNickname = barterUserNickname;
        this.barterGifticonList = barterGifticonList;
    }
}
