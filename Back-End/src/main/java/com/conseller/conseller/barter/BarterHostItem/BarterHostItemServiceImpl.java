package com.conseller.conseller.barter.BarterHostItem;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto.BarterHostItemDto;
import com.conseller.conseller.entity.BarterHostItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarterHostItemServiceImpl implements BarterHostItemService{

    private final BarterHostItemRepository barterHostItemRepository;
    @Override
    public Void addBarterHostItem(BarterHostItemDto barterHostItemDto) {
        BarterHostItem barterHostItem = barterHostItemDto.toEntity(barterHostItemDto);
        barterHostItemRepository.save(barterHostItem);
        return null;
    }
}
