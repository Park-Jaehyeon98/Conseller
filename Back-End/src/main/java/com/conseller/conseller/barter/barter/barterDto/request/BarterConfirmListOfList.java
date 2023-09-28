package com.conseller.conseller.barter.barter.barterDto.request;

import com.conseller.conseller.barter.barter.barterDto.response.BarterConfirmList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BarterConfirmListOfList {
    private String buyUserImageUrl;
    private String buyUserNickName;
    private Long buyUserIdx;
    private List<BarterConfirmList> barterTradeList;

    @Builder
    public BarterConfirmListOfList(String buyUserImageUrl, String buyUserNickName, Long buyUserIdx, List<BarterConfirmList> barterTradeList){
        this.buyUserImageUrl = buyUserImageUrl;
        this.buyUserNickName = buyUserNickName;
        this.buyUserIdx = buyUserIdx;
        this.barterTradeList = barterTradeList;
    }
}
