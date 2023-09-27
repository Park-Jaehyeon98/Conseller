package com.conseller.conseller.barter.barter.barterDto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BarterChangeRequest {
    private Long barterIdx;
    private Long barterRequestIdx;
}
