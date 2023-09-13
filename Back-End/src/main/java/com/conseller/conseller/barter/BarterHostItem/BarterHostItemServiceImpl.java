package com.conseller.conseller.barter.BarterHostItem;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto.BarterHostItemDto;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.BarterHostItem;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.gifticon.GifticonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarterHostItemServiceImpl implements BarterHostItemService{

    private final BarterHostItemRepository barterHostItemRepository;
    private final GifticonRepository gifticonRepository;
    @Override
    public Void addBarterHostItem(List<Long> gifticonIdx, Barter barter) {

        for(Long gift : gifticonIdx) {
            Gifticon gifticon = gifticonRepository.findById(gift).orElseThrow(()-> new RuntimeException());
            BarterHostItemDto barterHostItemDto = new BarterHostItemDto(barter, gifticon);
            BarterHostItem barterHostItem = barterHostItemDto.toEntity(barterHostItemDto);
            barterHostItemRepository.save(barterHostItem);
        }

        return null;
    }
}
