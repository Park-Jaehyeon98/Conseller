package com.conseller.conseller.barter.barter.barterService;

import com.conseller.conseller.barter.BarterHostItem.barterHostItemService.BarterHostItemService;
import com.conseller.conseller.barter.barter.BarterRepository;
import com.conseller.conseller.barter.barter.BarterRepositoryImpl;
import com.conseller.conseller.barter.barter.barterDto.mapper.BarterMapper;
import com.conseller.conseller.barter.barter.barterDto.request.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterFilterDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.response.BarterListResponse;
import com.conseller.conseller.barter.barter.barterDto.response.BarterResponseDto;
import com.conseller.conseller.barter.barter.enums.BarterStatus;
import com.conseller.conseller.barter.barterRequest.BarterRequestRepository;
import com.conseller.conseller.barter.barterRequest.enums.RequestStatus;
import com.conseller.conseller.category.subCategory.SubCategoryRepository;
import com.conseller.conseller.entity.*;
import com.conseller.conseller.gifticon.repository.GifticonRepository;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.user.UserRepository;
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
import java.util.List;

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


    //구매자 입장
    @Override
    public BarterListResponse getBarterList(BarterFilterDto barterFilterDto) {
        Pageable pageable = PageRequest.of(barterFilterDto.getPage() - 1, 10);

        Page<Barter> barterPage = barterRepositoryImpl.findBarterList(barterFilterDto, pageable);

        List<BarterResponseDto> barterResponseDtoList= new ArrayList<>();

        for(Barter barter : barterPage) {
            BarterResponseDto barterResponseDto = barter.toBarterResponseDto(barter);
            barterResponseDtoList.add(barterResponseDto);
        }

        BarterListResponse response = new BarterListResponse(barterResponseDtoList,
                barterPage.getTotalElements(),
                barterPage.getTotalPages());

        return response;
    }

    //판맨자 입장
    @Override
    public BarterResponseDto getBarter(Long barterIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx).orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));
        BarterResponseDto barterResponseDto = barter.toBarterResponseDto(barter);

        return barterResponseDto;
    }

    @Override
    public List<BarterResponseDto> getBarterListByHost(Long userIdx) {
        List<Barter> barterList = barterRepository.findByHostIdx(userIdx);
        List<BarterResponseDto> barterResponseDtoList = new ArrayList<>();
        for(Barter barter : barterList) {
            barterResponseDtoList.add(barter.toBarterResponseDto(barter));
        }
        return barterResponseDtoList;
    }

    @Override
    public Long addBarter(BarterCreateDto barterCreateDto) {
        User user = userRepository.findByUserIdx(barterCreateDto.getUserIdx()).orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));

        SubCategory subCategory = subCategoryRepository.findById(barterCreateDto.getSubCategory()).orElseThrow(() -> new RuntimeException("존재하지 않는 분류입니다."));
        SubCategory preferSubCategory = subCategoryRepository.findById(barterCreateDto.getPreferSubCategory()).orElseThrow(() -> new RuntimeException("존재하지 않는 분류입니다."));

        String date = barterCreateDto.getBarterEndDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");
        LocalDateTime endDate = LocalDateTime.parse(date, formatter);

        Barter barter = BarterMapper.INSTANCE.registBarterCreateToBarter(barterCreateDto, user, endDate, subCategory, preferSubCategory);

        try {
            barterHostItemService.addBarterHostItem(barterCreateDto.getSelectedItemIndices(), barter);
        } catch(Exception e) {
            barterRepository.deleteById(barter.getBarterIdx());
            throw new RuntimeException("보관 상태인 기프티콘만 등록할 수 있습니다.");
        }
        return barter.getBarterIdx();
    }

    @Override
    @Transactional
    public void modifyBarter(Long barterIdx, BarterModifyRequestDto barterModifyRequestDto) {
        SubCategory preferSubCategory = subCategoryRepository.findById(barterModifyRequestDto.getSubCategoryIdx())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 분류입니다."));

        SubCategory subCategory = subCategoryRepository.findById(barterModifyRequestDto.getSubCategoryIdx())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 분류입니다."));

        Barter barter = barterRepository.findByBarterIdx(barterIdx).orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));

        barter.modifyBarter(barterModifyRequestDto, subCategory, preferSubCategory);
    }

    @Override
    public void deleteBarter(Long barterIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx).orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));
        List<BarterRequest> barterRequestList = barterRequestRepository.findByBarterIdx(barterIdx);

        List<BarterHostItem> barterHostItemList = barter.getBarterHostItemList();
        for(BarterHostItem bhi : barterHostItemList) {
            Gifticon gift = bhi.getGifticon();
            gift.setGifticonStatus(GifticonStatus.KEEP.getStatus());
        }

        for(BarterRequest br : barterRequestList) {
            if(br.getBarterRequestStatus().equals(RequestStatus.REJECTED.getStatus())) continue;

            List<BarterGuestItem> barterGuestItemList = br.getBarterGuestItemList();

            for(BarterGuestItem bg : barterGuestItemList) {
                Gifticon gifticon = gifticonRepository.findById(bg.getGifticon().getGifticonIdx())
                        .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘입니다."));
                gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            }
        }

        barterRepository.deleteById(barterIdx);
    }

    @Override
    public void exchangeGifticon(Long barterIdx, Long barterRequestIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));
        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환 요청입니다."));
        //물물교환 교환신청 리스트
        List<BarterRequest> barterRequestList = barterRequestRepository.findByBarterIdx(barterIdx);

        barterRequest.setBarterRequestStatus(RequestStatus.ACCEPTED.getStatus());

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

            log.debug("거절까지 옵니다.");

            for(BarterGuestItem bg : barterGuestItemList) {
                Gifticon gifticon = gifticonRepository.findById(bg.getGifticon().getGifticonIdx())
                        .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘입니다."));
                gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
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

        }
        for(BarterGuestItem guestItem : barterRequest.getBarterGuestItemList()){
            Gifticon gift = guestItem.getGifticon();
            gift.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            gift.setUser(barterHost);
        }

        barter.setBarterStatus(BarterStatus.EXCHANGED.getStatus());
    }
}
