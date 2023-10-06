package com.conseller.conseller.barter.barter.barterDto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BarterConfirmRequestDTO {
    private Long barterIdx;
    private Long userIdx;
    private Boolean confirm;
}
