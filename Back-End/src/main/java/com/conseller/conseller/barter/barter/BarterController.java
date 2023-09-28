package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.request.*;
import com.conseller.conseller.barter.barter.barterDto.response.BarterDetailResponseDTO;
import com.conseller.conseller.barter.barter.barterDto.response.BarterResponse;
import com.conseller.conseller.barter.barter.barterDto.response.CreateBarterResponse;
import com.conseller.conseller.barter.barter.barterService.BarterService;
import com.conseller.conseller.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/barter")
@RequiredArgsConstructor
public class BarterController {

    private final BarterService barterService;
    private final NotificationService notificationService;

    //구매자 입장
    //1. 전체 물물교환 리스트 확인 - 필터 넣기 가능
    @PostMapping("/all")
    public ResponseEntity<BarterResponse> getAllBarterList(@RequestBody BarterFilterDto barterFilterDto) {
        return ResponseEntity.ok()
                .body(barterService.getBarterList(barterFilterDto));
    }
    //2. 물물교환글 자세히 보기
    @GetMapping("/{barterIdx}/{userIdx}")
    public ResponseEntity<BarterDetailResponseDTO> getBarter(@PathVariable Long barterIdx, @PathVariable Long userIdx) {
        return ResponseEntity.ok()
                .body(barterService.getBarter(barterIdx, userIdx));
    }

    //판매자 입장
    //1. 물물교환 작성
    @PostMapping("/new")
    public ResponseEntity<CreateBarterResponse> addBarter(@RequestBody BarterCreateDto barterCreateDto) {
        CreateBarterResponse createBarterResponse = new CreateBarterResponse();
        createBarterResponse.setBarterIdx(barterService.addBarter(barterCreateDto));
        return ResponseEntity.ok()
                .body(createBarterResponse);
    }
    //2. 물물교환 글 수정
    @PatchMapping("/{barterIdx}")
    public ResponseEntity<Void> modifyBarter(@PathVariable Long barterIdx, @RequestBody BarterModifyRequestDto barterModifyRequestDto) {
        barterService.modifyBarter(barterIdx, barterModifyRequestDto);
        return ResponseEntity.ok()
                .build();
    }
    //3. 자신의 물물교환글 삭제하기
    @DeleteMapping("/{barterIdx}")
    public ResponseEntity<Void> deleteBarter(@PathVariable Long barterIdx) {
        barterService.deleteBarter(barterIdx);
        return ResponseEntity.ok()
                .build();
    }
    //4. 자신의 물물교환 신청글에 달린 물물 교환 신청에 대해 선택하기
    @PatchMapping("/Confirm")
    public ResponseEntity<Void> selectBarterRequest(@RequestBody BarterConfirmRequestDTO barterConfirm) {

        if(barterConfirm.getConfirm()){
            barterService.exchangeGifticon(barterConfirm.getBarterIdx(), barterConfirm.getUserIdx());
            notificationService.sendBarterNotification(barterConfirm.getBarterIdx(), "물물교환 알림", 3);
        } else {
            barterService.rejectRequest(barterConfirm.getBarterIdx(), barterConfirm.getUserIdx());
        }

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/Confirm/{barterIdx}")
    public ResponseEntity<BarterConfirmPageResponseDTO> getConfirmPage(@PathVariable Long barterIdx) {
        return ResponseEntity.ok()
                .body(barterService.getBarterConfirmPage(barterIdx));
    }
}
