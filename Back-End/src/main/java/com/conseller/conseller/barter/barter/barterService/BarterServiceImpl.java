package com.conseller.conseller.barter.barter.barterService;

import com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemRepository;
import com.conseller.conseller.barter.BarterHostItem.BarterHostItemRepository;
import com.conseller.conseller.barter.BarterHostItem.barterHostItemService.BarterHostItemService;
import com.conseller.conseller.barter.barter.BarterRepository;
import com.conseller.conseller.barter.barter.BarterRepositoryImpl;
import com.conseller.conseller.barter.barter.barterDto.mapper.BarterMapper;
import com.conseller.conseller.barter.barter.barterDto.request.*;
import com.conseller.conseller.barter.barter.barterDto.response.*;
import com.conseller.conseller.barter.barter.enums.BarterStatus;
import com.conseller.conseller.barter.barterRequest.BarterRequestRepository;
import com.conseller.conseller.barter.barterRequest.enums.RequestStatus;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
import com.conseller.conseller.entity.*;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
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
            BarterItemData barterItemData = BarterMapper.INSTANCE.toBarterItemData(barter);
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
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));
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
                .barterUserIdx(barter.getBarterHost().getUserIdx())
                .barterUserProfileUrl(barter.getBarterHost().getUserProfileUrl())
                .barterUserDeposit(barter.getBarterHost().getUserDeposit())
                .barterUserNickname(barter.getBarterHost().getUserNickname())
                .barterRequestIdx(barterRequestIdx)
                .build();

        return barterDetailResponseDTO;
    }


    @Override
    public Long addBarter(BarterCreateDto barterCreateDto) {
        User user = userRepository.findByUserIdx(barterCreateDto.getUserIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));

        SubCategory preferSubCategory = subCategoryRepository.findById(barterCreateDto.getSubCategory()).orElseThrow(() -> new RuntimeException("존재하지 않는 분류입니다."));
        SubCategory subCategory = null;

        String date = barterCreateDto.getBarterEndDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime endDate = LocalDateTime.parse(date, formatter);

        Barter barter = BarterMapper.INSTANCE.registBarterCreateToBarter(barterCreateDto, user, endDate, subCategory, preferSubCategory);
        barterRepository.save(barter);
        Map<Integer, Integer> categoryMap = new HashMap<>();
        for(Long gifticonIdx : barterCreateDto.getSelectedItemIndices()) {
            Gifticon gifticon = gifticonRepository.findByGifticonIdx(gifticonIdx)
                    .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_NO_ITEM));
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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.SUB_CATEGORY_INVALID));
        barter.setSubCategory(barterSubCategory);

        try {
            LocalDateTime gifticonEndDate = barterHostItemService.addBarterHostItem(barterCreateDto.getSelectedItemIndices(), barter);
            barter.setBarterEndDate(gifticonEndDate);
            barterRepository.save(barter);
        } catch(Exception e) {
            barterRepository.deleteById(barter.getBarterIdx());
            throw new CustomException(CustomExceptionStatus.GIFTICON_NOT_KEEP);
        }
        return barter.getBarterIdx();
    }

    @Override
    @Transactional
    public void modifyBarter(Long barterIdx, BarterModifyRequestDto barterModifyRequestDto) {
        SubCategory preferSubCategory = subCategoryRepository.findBySubCategoryIdx(barterModifyRequestDto.getSubCategory())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.SUB_CATEGORY_INVALID));
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));

        barter.modifyBarter(barterModifyRequestDto, preferSubCategory);
    }

    @Override
    public void deleteBarter(Long barterIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));
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
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));
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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));
        User user = userRepository.findByUserIdx(userIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));


        Long barterRequestIdx = (long) -1;

        List<BarterRequest> findBarterRequestList = barter.getBarterRequestList();
        for(BarterRequest barterRequest : findBarterRequestList) {
            if(barterRequest.getUser().getUserIdx() == userIdx){
                barterRequestIdx = barterRequest.getBarterRequestIdx();
                break;
            }
        }
        if(barterRequestIdx == -1) throw new CustomException(CustomExceptionStatus.BARTER_REQUEST_INVALID);

        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_REQUEST_INVALID));
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
                        .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));
                gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
                gifticonRepository.save(gifticon);
            }
        }
        User barterHost = barter.getBarterHost();
        User barterRequester = barterRequest.getUser();

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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));

        Long barterRequestIdx = (long) -1;

        List<BarterRequest> findBarterRequestList = barter.getBarterRequestList();
        for(BarterRequest barterRequest : findBarterRequestList) {
            if(barterRequest.getUser().getUserIdx() == userIdx){
                barterRequestIdx = barterRequest.getBarterRequestIdx();
                break;
            }
        }
        if(barterRequestIdx == -1) throw new CustomException(CustomExceptionStatus.BARTER_REQUEST_INVALID);
        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_REQUEST_INVALID));

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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));

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

    @Override
    public List<Barter> getExpiredBarterList() {
        return barterRepository.findBarterAllExpired();
    }

    @Override
    public BarterItemData getPopularBarter() {
        Long popularBarterIdx = (long) 0;
        Integer barterRequestCount = 0;

        List<Barter> barterList = barterRepository.findAll();
        for(Barter barter : barterList) {
            if(barter.getBarterRequestList().size() > barterRequestCount) {
                barterRequestCount = barter.getBarterRequestList().size();
                popularBarterIdx = barter.getBarterIdx();
            }
        }

        Barter barter = barterRepository.findByBarterIdx(popularBarterIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));



        return BarterMapper.INSTANCE.toBarterItemData(barter);
    }
}
