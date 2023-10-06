package com.conseller.conseller.barter.BarterGuestItem.barterGuestItemService;

import com.conseller.conseller.entity.BarterRequest;

import java.util.List;

public interface BarterGuestItemService {

    Void addBarterGuestItem(List<Long> gifticonList, BarterRequest barterRequest);
}
