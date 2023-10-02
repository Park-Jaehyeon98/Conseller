package com.conseller.conseller.barter.barter.barterDto.response;

import lombok.*;


@Getter @Setter @NoArgsConstructor
public class BarterItemData {
    private Long barterIdx;
    private String gifticonDataImageName;
    private String gifticonName;
    private String gifticonEndDate;
    private String barterEndDate;
    private Boolean deposit;
    private String preper;
    private String barterName;

    @Builder
    public BarterItemData(Long barterIdx, String gifticonDataImageName, String gifticonName,
                          String gifticonEndDate, String barterEndDate, Boolean deposit, String preper, String barterName) {
        this.barterIdx = barterIdx;
        this.gifticonDataImageName = gifticonDataImageName;
        this.gifticonName = gifticonName;
        this.gifticonEndDate = gifticonEndDate;
        this.barterEndDate = barterEndDate;
        this.deposit = deposit;
        this.preper = preper;
        this.barterName = barterName;
    }

}
