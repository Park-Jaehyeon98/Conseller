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
public class BarterConfirmPageResponseDTO {
    private String barterName;
    private String barterText;
    private List<BarterConfirmList> barterConfirmList;
    private List<BarterConfirmListOfList> barterTradeAllList;

    @Builder
    public BarterConfirmPageResponseDTO(String barterName, String barterText, List<BarterConfirmList> barterConfirmList, List<BarterConfirmListOfList> barterTradeAllList) {
        this.barterName = barterName;
        this.barterText = barterText;
        this.barterConfirmList = barterConfirmList;
        this.barterTradeAllList = barterTradeAllList;
    }
}
