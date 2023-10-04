package com.conseller.conseller.barter.barterRequest.barterRequestService;

import com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemDto.BarterGuestItemDto;
import com.conseller.conseller.barter.BarterGuestItem.BarterGuestItemRepository;
import com.conseller.conseller.barter.BarterGuestItem.barterGuestItemService.BarterGuestItemService;
import com.conseller.conseller.barter.barter.BarterRepository;
import com.conseller.conseller.barter.barter.enums.BarterStatus;
import com.conseller.conseller.barter.barterRequest.BarterRequestRepository;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestRegistDto;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestResponseDto;
import com.conseller.conseller.barter.barterRequest.enums.RequestStatus;
import com.conseller.conseller.entity.*;
import com.conseller.conseller.exception.CustomException;
import com.conseller.conseller.exception.CustomExceptionStatus;
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
    private final BarterGuestItemRepository barterGuestItemRepository;
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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));
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
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));
        String statusOfBarter = barter.getBarterStatus();
        if(!statusOfBarter.equals(BarterStatus.EXCHANGEABLE.getStatus()) && !statusOfBarter.equals(BarterStatus.SUGGESTED.getStatus())){
            throw new CustomException(CustomExceptionStatus.BARTER_EXPIRED_INVALID);
        }

        List<BarterRequest> barterRequestList = barter.getBarterRequestList();
        for(BarterRequest bq : barterRequestList) {
            if(bq.getUser().getUserIdx() == barterRequestRegistDto.getUserIdx()) {
                throw new CustomException(CustomExceptionStatus.BARTER_ALREADY_SEND);
            }
        }

        User user = userRepository.findByUserIdx(barterRequestRegistDto.getUserIdx())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.USER_INVALID));
        BarterRequest barterRequest = barterRequestRegistDto.toEntity(barter, user);
        barterRequestRepository.save(barterRequest);
        if(statusOfBarter.equals(BarterStatus.EXCHANGEABLE.getStatus())) {
            barter.setBarterStatus(BarterStatus.SUGGESTED.getStatus());
            barterRepository.save(barter);
        }

        try{
            barterGuestItemService.addBarterGuestItem(barterRequestRegistDto.getBarterGuestItemList(), barterRequest);
        } catch(Exception e) {
            barterRequestRepository.deleteById(barterRequest.getBarterRequestIdx());
            throw new CustomException(CustomExceptionStatus.GIFTICON_NOT_KEEP);
        }

    }

    @Override
    public void deleteBarterRequest(Long barterRequestIdx) {
        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_REQUEST_INVALID));
        List<BarterGuestItem> barterGuestItemList = barterRequest.getBarterGuestItemList();

        List<BarterGuestItemDto> barterGuestItemDtoList = new ArrayList<>();
        for(BarterGuestItem bgi : barterGuestItemList) {
            Gifticon gifticon = gifticonRepository.findById(bgi.getGifticon().getGifticonIdx())
                    .orElseThrow(() -> new CustomException(CustomExceptionStatus.GIFTICON_INVALID));
            gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            gifticonRepository.save(gifticon);

            barterGuestItemRepository.deleteById(bgi.getBarterGuestItemIdx());
        }

        barterRequestRepository.deleteById(barterRequestIdx);
    }

    @Override
    public void rejectByTimeBarterRequest(Long barterRequestIdx) {
        BarterRequest barterRequest = barterRequestRepository.findByBarterRequestIdx(barterRequestIdx)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.BARTER_INVALID));
        List<BarterGuestItem> barterGuestItemList =  barterRequest.getBarterGuestItemList();
        for(BarterGuestItem bgi : barterGuestItemList) {
            Gifticon gifticon = bgi.getGifticon();
            gifticon.setGifticonStatus(GifticonStatus.KEEP.getStatus());
            gifticonRepository.save(gifticon);
        }
        barterRequest.setBarterRequestStatus(RequestStatus.REJECTED.getStatus());
        barterRequestRepository.save(barterRequest);
    }
}
