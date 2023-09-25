package com.conseller.conseller.barter.BarterGuestItem.barterGuestItemService;

import com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemRepository;
import com.conseller.conseller.entity.BarterGuestItem;
import com.conseller.conseller.entity.BarterRequest;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
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
        for(Long gifticonIdx : gifticonList) {
            Gifticon gifticon = gifticonRepository.findById(gifticonIdx)
                    .orElseThrow(()-> new RuntimeException());
            if(!gifticon.getGifticonStatus().equals(GifticonStatus.KEEP)) {
                throw new RuntimeException();
            }
        }


        for(Long gifticonIdx : gifticonList) {
            Gifticon gifticon = gifticonRepository.findById(gifticonIdx)
                    .orElseThrow(() -> new RuntimeException());
            BarterGuestItem barterGuestItem = new BarterGuestItem(barterRequest, gifticon);
            gifticon.setGifticonStatus(GifticonStatus.BARTER.getStatus());
            barterGuestItemRepository.save(barterGuestItem);
        }
        return null;
    }
}
