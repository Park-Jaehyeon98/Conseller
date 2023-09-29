package com.conseller.conseller.barter.barter.barterService;

import com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemRepository;
import com.conseller.conseller.barter.BarterHostItem.BarterHostItemRepository;
import com.conseller.conseller.barter.BarterHostItem.barterHostItemService.BarterHostItemService;
import com.conseller.conseller.barter.barter.BarterRepository;
import com.conseller.conseller.barter.barter.BarterRepositoryImpl;
import com.conseller.conseller.barter.barter.barterDto.mapper.BarterMapper;
import com.conseller.conseller.barter.barter.barterDto.request.*;
import com.conseller.conseller.barter.barter.barterDto.response.BarterConfirmList;
import com.conseller.conseller.barter.barter.barterDto.response.BarterDetailResponseDTO;
import com.conseller.conseller.barter.barter.barterDto.response.BarterItemData;
import com.conseller.conseller.barter.barter.barterDto.response.BarterResponse;
import com.conseller.conseller.barter.barter.enums.BarterStatus;
import com.conseller.conseller.barter.barterRequest.BarterRequestRepository;
import com.conseller.conseller.barter.barterRequest.enums.RequestStatus;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
import com.conseller.conseller.entity.*;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.gifticon.repository.GifticonRepository;
import com.conseller.conseller.user.UserRepository;
import com.conseller.conseller.utils.DateTimeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class BarterServiceImpl implements BarterService{

    private final BarterRepository barterRepository;
    private final BarterRepositoryImpl barterRepositoryImpl;
    private final UserRepository userRepository;
    private final GifticonRepository gifticonRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final BarterHostItemService barterHostItemService;
    private final BarterRequestRepository barterRequestRepository;
    private final BarterHostItemRepository barterHostItemRepository;
    private final BarterGuestItemRepository barterGuestItemRepository;


    //구매자 입장
    @Override
    public BarterResponse getBarterList(BarterFilterDto barterFilterDto) {
        Pageable pageable = PageRequest.of(barterFilterDto.getPage() - 1, 10);

        Page<Barter> barterPage = barterRepositoryImpl.findBarterList(barterFilterDto, pageable);

        List<BarterItemData> barterItemDataList= new ArrayList<>();

        for(Barter barter : barterPage) {
            List<BarterHostItem> barterHostItemList = barter.getBarterHostItemList();
            Gifticon gifticon = null;

            for(BarterHostItem gift : barterHostItemList) {
                if(gift.getGifticon().getSubCategory() == barter.getSubCategory()) {
                    gifticon = gift.getGifticon();
                    break;
                }
            }

            Boolean deposit = false;
            if(barter.getBarterHost().getUserDeposit() > 0) {
                deposit = true;
            }
            BarterItemData barterItemData = barter.toBarterItemData(barter, gifticon, deposit);
            barterItemDataList.add(barterItemData);
        }

        BarterResponse response = new BarterResponse(
                barterPage.getTotalPages(),
                barterPage.getTotalElements(),
                barterItemDataList);

        return response;
    }

    //판맨자 입장
    @Override
    public BarterDetailResponseDTO getBarter(Long barterIdx, Long userIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx).orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));
        List<BarterHostItem> barterHostItemList = barter.getBarterHostItemList();
        List<BarterConfirmList> barterGifticonList = new ArrayList<>();
        for(BarterHostItem bhi : barterHostItemList) {
            Gifticon gifticon = bhi.getGifticon();
            BarterConfirmList barterConfirmList = BarterConfirmList.builder()
                    .gifticonDataImageName(gifticon.getGifticonDataImageUrl())
                    .gifticonName(gifticon.getGifticonName())
                    .gifticonEndDate(DateTimeConverter.getInstance().convertString(gifticon.getGifticonEndDate()))
                    .build();
            barterGifticonList.add(barterConfirmList);
        }
        Long barterRequestIdx = (long) 0;
        List<BarterRequest> barterRequestList = barter.getBarterRequestList();
        for(BarterRequest bq : barterRequestList) {
            if(bq.getUser().getUserIdx() == userIdx) {
                barterRequestIdx = bq.getBarterRequestIdx();
                break;
            }
        }

        BarterDetailResponseDTO barterDetailResponseDTO = BarterDetailResponseDTO.builder()
                .barterImageList(barterGifticonList)
                .preper(barter.getPreferSubCategory().getSubCategoryContent())
                .barterName(barter.getBarterName())
                .barterText(barter.getBarterText())
                .barterUserIdx(barter.getBarterIdx())
                .barterUserProfileUrl(barter.getBarterHost().getUserProfileUrl())
                .barterUserDeposit(barter.getBarterHost().getUserDeposit())
                .barterUserNickname(barter.getBarterHost().getUserNickname())
                .barterRequestIdx(barterRequestIdx)
                .build();

        return barterDetailResponseDTO;
    }


    @Override
    public Long addBarter(BarterCreateDto barterCreateDto) {
        User user = userRepository.findByUserIdx(barterCreateDto.getUserIdx()).orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));

        SubCategory preferSubCategory = subCategoryRepository.findById(barterCreateDto.getSubCategory()).orElseThrow(() -> new RuntimeException("존재하지 않는 분류입니다."));
        SubCategory subCategory = null;


        String date = barterCreateDto.getBarterEndDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");
        LocalDateTime endDate = LocalDateTime.parse(date, formatter);

        Barter barter = BarterMapper.INSTANCE.registBarterCreateToBarter(barterCreateDto, user, endDate, subCategory, preferSubCategory);
        barterRepository.save(barter);
        Map<Integer, Integer> categoryMap = new HashMap<>();
        for(Long gifticonIdx : barterCreateDto.getSelectedItemIndices()) {
            Gifticon gifticon = gifticonRepository.findByGifticonIdx(gifticonIdx)
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘입니다."));
            if(categoryMap.containsKey(gifticon.getSubCategory().getSubCategoryIdx())) {
                int k = categoryMap.get(gifticon.getSubCategory().getSubCategoryIdx());
                categoryMap.put(gifticon.getSubCategory().getSubCategoryIdx(), k+1);
            } else {
                categoryMap.put(gifticon.getSubCategory().getSubCategoryIdx(), 1);
            }
        }
        int max_count = 0;
        int max_category = 0;
        for(Integer key : categoryMap.keySet() ) {
            if(categoryMap.get(key) > max_count) {
                max_count = categoryMap.get(key);
                max_category = key;
            }
        }
        SubCategory barterSubCategory = subCategoryRepository.findBySubCategoryIdx(max_category)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 분류"));
        barter.setSubCategory(barterSubCategory);

        try {
            LocalDateTime gifticonEndDate = barterHostItemService.addBarterHostItem(barterCreateDto.getSelectedItemIndices(), barter);
            barter.setBarterEndDate(gifticonEndDate);
        } catch(Exception e) {
            barterRepository.deleteById(barter.getBarterIdx());
            throw new RuntimeException("보관 상태인 기프티콘만 등록할 수 있습니다.");
        }
        return barter.getBarterIdx();
    }

    @Override
    @Transactional
    public void modifyBarter(Long barterIdx, BarterModifyRequestDto barterModifyRequestDto) {
        SubCategory preferSubCategory = subCategoryRepository.findBySubCategoryIdx(barterModifyRequestDto.getSubCategory())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 분류입니다."));
        Barter barter = barterRepository.findByBarterIdx(barterIdx).orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));

        barter.modifyBarter(barterModifyRequestDto, preferSubCategory);
    }

    @Override
    public void deleteBarter(Long barterIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx).orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));
        List<BarterRequest> barterRequestList = barterRequestRepository.findByBarterIdx(barterIdx);

        List<BarterHostItem> barterHostItemList = barter.getBarterHostItemList();
        for(BarterHostItem bhi : barterHostItemList) {
            Gifticon gift = bhi.getGifticon();
            gift.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            barterHostItemRepository.deleteById(bhi.getBarterHostItemIdx());
        }

        for(BarterRequest br : barterRequestList) {
            if(br.getBarterRequestStatus().equals(RequestStatus.REJECTED.getStatus())) continue;

            List<BarterGuestItem> barterGuestItemList = br.getBarterGuestItemList();

            for(BarterGuestItem bg : barterGuestItemList) {
                Gifticon gifticon = gifticonRepository.findById(bg.getGifticon().getGifticonIdx())
                        .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘입니다."));
                gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
                barterGuestItemRepository.deleteById(bg.getBarterGuestItemIdx());
            }
            barterRequestRepository.deleteById(br.getBarterRequestIdx());
        }

        barterRepository.deleteById(barterIdx);
    }

    @Override
    public void exchangeGifticon(Long barterIdx, Long userIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new RuntimeException( "존재하지 않는 유저입니다."));


        Long barterRequestIdx = (long) -1;

        List<BarterRequest> findBarterRequestList = barter.getBarterRequestList();
        for(BarterRequest barterRequest : findBarterRequestList) {
            if(barterRequest.getUser().getUserIdx() == userIdx){
                barterRequestIdx = barterRequest.getBarterRequestIdx();
                break;
            }
        }
        if(barterRequestIdx == -1) throw new RuntimeException("불가");

        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환 요청입니다."));
        //물물교환 교환신청 리스트
        List<BarterRequest> barterRequestList = barterRequestRepository.findByBarterIdx(barterIdx);

        barterRequest.setBarterRequestStatus(RequestStatus.ACCEPTED.getStatus());
        barterRequestRepository.save(barterRequest);

        for(BarterRequest br : barterRequestList) {

            try{
                if(br.getBarterRequestIdx() == barterRequestIdx) continue;
            } catch (Exception e) {
                throw new RuntimeException("요청 인덱스 에러입니다.");
            }
            try {
                if(br.getBarterRequestStatus().equals(RequestStatus.REJECTED.getStatus())) continue;
            } catch (Exception e) {
                throw new RuntimeException("거절된 요청 에러입니다.");
            }

            List<BarterGuestItem> barterGuestItemList = br.getBarterGuestItemList();
            br.setBarterRequestStatus(RequestStatus.REJECTED.getStatus());
            barterRequestRepository.save(br);


            for(BarterGuestItem bg : barterGuestItemList) {
                Gifticon gifticon = gifticonRepository.findById(bg.getGifticon().getGifticonIdx())
                        .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘입니다."));
                gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
                gifticonRepository.save(gifticon);
            }
        }
        User barterHost = barter.getBarterHost();
        User barterRequester = barterRequest.getUser();

        log.debug("유저 선정까지 옵니다.");

        List<Gifticon> hostItems = new ArrayList<>();
        for(BarterHostItem hostItem : barter.getBarterHostItemList()){
            Gifticon gift = hostItem.getGifticon();
            gift.setUser(barterRequester);
            gift.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            gifticonRepository.save(gift);
        }
        for(BarterGuestItem guestItem : barterRequest.getBarterGuestItemList()){
            Gifticon gift = guestItem.getGifticon();
            gift.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            gift.setUser(barterHost);
            gifticonRepository.save(gift);
        }

        barter.setBarterStatus(BarterStatus.EXCHANGED.getStatus());
        barter.setBarterCompletedDate(now());
        barter.setBarterCompleteGuest(user);
        barterRepository.save(barter);
    }

    @Override
    public void rejectRequest(Long barterIdx, Long userIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));

        Long barterRequestIdx = (long) -1;

        List<BarterRequest> findBarterRequestList = barter.getBarterRequestList();
        for(BarterRequest barterRequest : findBarterRequestList) {
            if(barterRequest.getUser().getUserIdx() == userIdx){
                barterRequestIdx = barterRequest.getBarterRequestIdx();
                break;
            }
        }
        if(barterRequestIdx == -1) throw new RuntimeException("불가");
        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환 요청입니다."));

        barterRequest.setBarterRequestStatus(RequestStatus.REJECTED.getStatus());
        barterRequestRepository.save(barterRequest);

        List<BarterGuestItem> gifticonList = barterRequest.getBarterGuestItemList();
        for(BarterGuestItem bgi : gifticonList) {
            Gifticon gifticon = bgi.getGifticon();
            gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            gifticonRepository.save(gifticon);
        }
    }

    @Override
    public BarterConfirmPageResponseDTO getBarterConfirmPage(Long barterIdx) {

        //barter 정보들
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환글입니다."));

        //barter의 기프티콘 리스트
        List<BarterConfirmList> hostGifticons = new ArrayList<>();
        List<BarterHostItem> barterHostItemList = barter.getBarterHostItemList();
        for(BarterHostItem bhi : barterHostItemList) {
            BarterConfirmList barterConfirmList = BarterConfirmList.builder()
                    .gifticonDataImageName(bhi.getGifticon().getGifticonDataImageUrl())
                    .gifticonName(bhi.getGifticon().getGifticonName())
                    .gifticonEndDate(DateTimeConverter.getInstance().convertString(bhi.getGifticon().getGifticonEndDate()))
                    .build();
            hostGifticons.add(barterConfirmList);
        }

        List<BarterRequest> barterRequestList = barter.getBarterRequestList();
        List<BarterConfirmListOfList> barterConfirmListOfLists = new ArrayList<>();
        for(BarterRequest bq : barterRequestList) {
            List<BarterGuestItem> barterGuestItemList = bq.getBarterGuestItemList();
            List<BarterConfirmList> barterConfirmLists = new ArrayList<>();
            for(BarterGuestItem bgi : barterGuestItemList) {
                BarterConfirmList barterConfirmList = BarterConfirmList.builder()
                        .gifticonDataImageName(bgi.getGifticon().getGifticonDataImageUrl())
                        .gifticonName(bgi.getGifticon().getGifticonName())
                        .gifticonEndDate(DateTimeConverter.getInstance().convertString(bgi.getGifticon().getGifticonEndDate()))
                        .build();
                barterConfirmLists.add(barterConfirmList);
            }
            BarterConfirmListOfList barterConfirmListOfList = BarterConfirmListOfList.builder()
                    .buyUserImageUrl(bq.getUser().getUserProfileUrl())
                    .buyUserNickName(bq.getUser().getUserNickname())
                    .buyUserIdx(bq.getUser().getUserIdx())
                    .barterTradeList(barterConfirmLists)
                    .build();
            barterConfirmListOfLists.add(barterConfirmListOfList);
        }


        return BarterConfirmPageResponseDTO.builder()
                .barterName(barter.getBarterName())
                .barterText(barter.getBarterText())
                .barterConfirmList(hostGifticons)
                .barterTradeAllList(barterConfirmListOfLists)
                .build();
    }
}
