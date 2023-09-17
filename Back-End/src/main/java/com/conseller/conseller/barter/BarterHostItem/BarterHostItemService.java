package com.conseller.conseller.barter.BarterHostItem;

import com.conseller.conseller.entity.Barter;

import java.util.List;

public interface BarterHostItemService {
    Void addBarterHostItem(List<Long> gifticonIdx, Barter barter);
}
