package com.conseller.conseller.barter.barterRequest.barterRequestService;

import com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemDto.BarterGuestItemDto;
import com.conseller.conseller.barter.BarterGuestItem.barterGuestItemService.BarterGuestItemService;
import com.conseller.conseller.barter.barter.BarterRepository;
import com.conseller.conseller.barter.barterRequest.BarterRequestRepository;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestRegistDto;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestResponseDto;
import com.conseller.conseller.entity.*;
import com.conseller.conseller.gifticon.repository.GifticonRepository;
import com.conseller.conseller.gifticon.enums.GifticonStatus;
import com.conseller.conseller.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarterRequestServiceImpl implements BarterRequestService{

    private final BarterRequestRepository barterRequestRepository;
    private final BarterGuestItemService barterGuestItemService;
    private final GifticonRepository gifticonRepository;
    private final BarterRepository barterRepository;
    private final UserRepository userRepository;
    List<BarterRequestResponseDto> barterRequestResponseDtoList;
    @Override
    public List<BarterRequestResponseDto> getBarterRequestList() {
        List<BarterRequest> barterRequestList = barterRequestRepository.findAll();
        List<BarterRequestResponseDto> barterRequestResponseDtoList = new ArrayList<>();

        for(BarterRequest barterRequest : barterRequestList) {
            BarterRequestResponseDto barterRequestResponseDto = barterRequest.toBarterRequestResponseDto(barterRequest);
            barterRequestResponseDtoList.add(barterRequestResponseDto);
        }

        return barterRequestResponseDtoList;
    }

    @Override
    public BarterRequestResponseDto getBarterRequest(Long barterRequestIdx) {
        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환 요청입니다."));
        BarterRequestResponseDto barterRequestResponseDto = barterRequest.toBarterRequestResponseDto(barterRequest);

        return barterRequestResponseDto;
    }

    @Override
    public List<BarterRequestResponseDto> getBarterRequestListByBarterIdx(Long barterIdx) {
        List<BarterRequest> barterRequestList = barterRequestRepository.findByBarterIdx(barterIdx);
        barterRequestResponseDtoList = new ArrayList<>();

        for(BarterRequest barterRequest : barterRequestList) {
            BarterRequestResponseDto barterRequestResponseDto = barterRequest.toBarterRequestResponseDto(barterRequest);
            barterRequestResponseDtoList.add(barterRequestResponseDto);
        }

        return barterRequestResponseDtoList;
    }

    @Override
    public List<BarterRequestResponseDto> getBarterRequestListByRequester(Long userIdx) {
        List<BarterRequest> barterRequestList = barterRequestRepository.findByUserIdx(userIdx);
        barterRequestResponseDtoList = new ArrayList<>();

        for(BarterRequest barterRequest : barterRequestList) {
            BarterRequestResponseDto barterRequestResponseDto = barterRequest.toBarterRequestResponseDto(barterRequest);
            barterRequestResponseDtoList.add(barterRequestResponseDto);
        }

        return barterRequestResponseDtoList;
    }

    @Override
    public void addBarterRequest(BarterRequestRegistDto barterRequestRegistDto, Long barterIdx) {
        Barter barter = barterRepository.findByBarterIdx(barterIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환입니다."));

        List<BarterRequest> barterRequestList = barter.getBarterRequestList();
        for(BarterRequest bq : barterRequestList) {
            if(bq.getUser().getUserIdx() == barterRequestRegistDto.getUserIdx()) {
                throw new RuntimeException("이미 교환 요청 보낸 글입니다.");
            }
        }

        User user = userRepository.findByUserIdx(barterRequestRegistDto.getUserIdx())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
        BarterRequest barterRequest = barterRequestRegistDto.toEntity(barter, user);
        barterRequestRepository.save(barterRequest);

        try{
            barterGuestItemService.addBarterGuestItem(barterRequestRegistDto.getBarterGuestItemList(), barterRequest);
        } catch(Exception e) {
            barterRequestRepository.deleteById(barterRequest.getBarterRequestIdx());
            throw new RuntimeException("보관 상태인 기프티콘만 등록할 수 있습니다.");
        }

    }

    @Override
    public void deleteBarterRequest(Long barterRequestIdx) {
        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 교환 요청입니다."));
        List<BarterGuestItem> barterGuestItemList = barterRequest.getBarterGuestItemList();

        List<BarterGuestItemDto> barterGuestItemDtoList = new ArrayList<>();
        for(BarterGuestItem bgi : barterGuestItemList) {
            Gifticon gifticon = gifticonRepository.findById(bgi.getGifticon().getGifticonIdx())
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 기프티콘입니다."));
            gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            gifticonRepository.save(gifticon);
        }

        barterRequestRepository.deleteById(barterRequestIdx);
    }
}
