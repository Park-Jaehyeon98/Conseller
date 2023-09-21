package com.conseller.conseller.barter.BarterGuestItem.barterGuestItemService;

import com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemRepository;
import com.conseller.conseller.entity.BarterGuestItem;
import com.conseller.conseller.entity.BarterRequest;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.gifticon.GifticonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarterGuestItemServiceImpl implements BarterGuestItemService{

    private final BarterGuestItemRepository barterGuestItemRepository;
    private final GifticonRepository gifticonRepository;

    @Override
    public Void addBarterGuestItem(List<Long> gifticonList, BarterRequest barterRequest) {

        for(Long gift : gifticonList) {
            Gifticon gifticon = gifticonRepository.findById(gift)
                    .orElseThrow(() -> new RuntimeException());
            BarterGuestItem barterGuestItem = new BarterGuestItem(barterRequest, gifticon);
            barterGuestItemRepository.save(barterGuestItem);
        }
        return null;
    }
}
