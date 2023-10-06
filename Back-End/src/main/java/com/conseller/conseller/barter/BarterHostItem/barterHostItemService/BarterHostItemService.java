package com.conseller.conseller.barter.BarterHostItem.barterHostItemService;

import com.conseller.conseller.entity.Barter;

import java.time.LocalDateTime;
import java.util.List;

public interface BarterHostItemService {
    LocalDateTime addBarterHostItem(List<Long> gifticonIdx, Barter barter);
}
