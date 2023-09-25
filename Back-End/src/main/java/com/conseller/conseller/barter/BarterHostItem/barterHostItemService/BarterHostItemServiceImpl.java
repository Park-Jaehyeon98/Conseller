package com.conseller.conseller.barter.BarterHostItem.barterHostItemService;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemRepository;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.BarterHostItem;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarterHostItemServiceImpl implements BarterHostItemService{

    private final BarterHostItemRepository barterHostItemRepository;
    private final GifticonRepository gifticonRepository;
    @Override
    public Void addBarterHostItem(List<Long> gifticons, Barter barter) {
        for(Long gifticonIdx : gifticons) {
            Gifticon gifticon = gifticonRepository.findById(gifticonIdx)
                    .orElseThrow(()-> new RuntimeException());
            if(!gifticon.getGifticonStatus().equals(GifticonStatus.KEEP)) {
                throw new RuntimeException();
            }
        }

        for(Long gifticonIdx : gifticons) {
            Gifticon gifticon = gifticonRepository.findById(gifticonIdx)
                    .orElseThrow(()-> new RuntimeException());
            BarterHostItem barterHostItem = new BarterHostItem(barter, gifticon);
            gifticon.setGifticonStatus(GifticonStatus.BARTER.getStatus());
            barterHostItemRepository.save(barterHostItem);
        }
        return null;
    }
}
