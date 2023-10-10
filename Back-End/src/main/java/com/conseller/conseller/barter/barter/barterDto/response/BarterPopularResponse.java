package com.conseller.conseller.barter.barter.barterDto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BarterPopularResponse {
    private List<BarterItemData> items;
}
