package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.request.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterFilterDto;
import com.conseller.conseller.barter.barter.barterDto.request.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.response.BarterResponseDto;
import com.conseller.conseller.barter.barter.barterService.BarterService;
import com.conseller.conseller.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/barter")
@RequiredArgsConstructor
public class BarterController {

    private final BarterService barterService;
    private final NotificationService notificationService;

    //구매자 입장
    //1. 전체 물물교환 리스트 확인 - 필터 넣기 가능
    @PostMapping({"", "/"})
    public ResponseEntity<List<BarterResponseDto>> getBarterList(@RequestBody BarterFilterDto barterFilterDto) {
        return ResponseEntity.ok()
                .body(barterService.getBarterList(barterFilterDto));
    }
    //2. 물물교환글 자세히 보기
    @GetMapping("/barter/{barterIdx}")
    public ResponseEntity<BarterResponseDto> getBarter(@PathVariable Long barterIdx) {
        return ResponseEntity.ok()
                .body(barterService.getBarter(barterIdx));
    }
    //3. 자신의 물물교환 글 전체보기
    @GetMapping("/user/{userIdx}")
    public ResponseEntity<List<BarterResponseDto>> getBarterListByUserIdx(@PathVariable Long userIdx) {
        return ResponseEntity.ok()
                .body(barterService.getBarterListByHost(userIdx));
    }


    //판매자 입장
    //1. 물물교환 작성
    @PostMapping("/new")
    public ResponseEntity<Long> addBarter(@RequestBody BarterCreateDto barterCreateDto) {
        return ResponseEntity.ok()
                .body(barterService.addBarter(barterCreateDto));
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
    @PatchMapping("/{barterIdx}/{barterRequestIdx}")
    public ResponseEntity<Void> selectBarterRequest(@PathVariable Long barterIdx, @PathVariable Long barterRequestIdx) {
        barterService.exchangeGifticon(barterIdx, barterRequestIdx);

        notificationService.sendBarterNotification(barterIdx, "물물교환 알림", 3);

        return ResponseEntity.ok()
                .build();
    }
}
