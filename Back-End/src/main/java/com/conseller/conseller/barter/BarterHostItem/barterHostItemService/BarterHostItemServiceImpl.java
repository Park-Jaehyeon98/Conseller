package com.conseller.conseller.barter.BarterHostItem.barterHostItemService;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemRepository;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.BarterHostItem;
import com.conseller.conseller.entity.Gifticon;
import com.conseller.conseller.gifticon.GifticonRepository;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BarterHostItemServiceImpl implements BarterHostItemService{

    private final BarterHostItemRepository barterHostItemRepository;
    private final GifticonRepository gifticonRepository;
    @Override
    public LocalDateTime addBarterHostItem(List<Long> gifticons, Barter barter) {
        for(Long gifticonIdx : gifticons) {
            Gifticon gifticon = gifticonRepository.findById(gifticonIdx)
                    .orElseThrow(()-> new RuntimeException("존재하지 않는 기프티콘입니다."));
            if(!gifticon.getGifticonStatus().equals(GifticonStatus.KEEP.getStatus())) {
                throw new RuntimeException("보관 상태인 기프티콘만 등록할 수 있습니다.");
            }
        }

        LocalDateTime recentExpireDate = null;


        for(Long gifticonIdx : gifticons) {
            Gifticon gifticon = gifticonRepository.findById(gifticonIdx)
                    .orElseThrow(()-> new RuntimeException("존재하지 않는 기프티콘입니다."));
            if(recentExpireDate == null || gifticon.getGifticonEndDate().isBefore(recentExpireDate)) {
                recentExpireDate = gifticon.getGifticonEndDate();
            }

            BarterHostItem barterHostItem = new BarterHostItem(barter, gifticon);
            gifticon.setGifticonStatus(GifticonStatus.BARTER.getStatus());
            barterHostItemRepository.save(barterHostItem);
        }

        return recentExpireDate;
    }
}
