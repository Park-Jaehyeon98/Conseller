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
                    .orElseThrow(()-> new RuntimeException("존재하지 않는 기프티콘입니다."));
            if(!gifticon.getGifticonStatus().equals(GifticonStatus.KEEP.getStatus())) {
                throw new RuntimeException("보관 상태인 기프티콘만 등록할 수 있습니다.");
            }
        }


        for(Long gifticonIdx : gifticonList) {
            Gifticon gifticon = gifticonRepository.findById(gifticonIdx)
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘입니다."));
            BarterGuestItem barterGuestItem = new BarterGuestItem(barterRequest, gifticon);
            gifticon.setGifticonStatus(GifticonStatus.BARTER.getStatus());
            barterGuestItemRepository.save(barterGuestItem);
        }
        return null;
    }
}
