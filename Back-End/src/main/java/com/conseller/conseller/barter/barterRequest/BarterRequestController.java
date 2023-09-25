package com.conseller.conseller.barter.barterRequest;

import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestRegistDto;
import com.conseller.conseller.barter.barterRequest.barterRequestDto.BarterRequestResponseDto;
import com.conseller.conseller.barter.barterRequest.barterRequestService.BarterRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/barterRequest")
public class BarterRequestController {

    private final BarterRequestService barterRequestService;

    // 판매자 입장
    //1. 전체 교환 요청보기 - 관리자 아니면 쓸 일 없을 듯
    @GetMapping({"","/"})
    public ResponseEntity<List<BarterRequestResponseDto>> getBarterRequestList() {
        return ResponseEntity.ok()
                .body(barterRequestService.getBarterRequestList());
    }
    // 2. 자신의 물물교환에 대한 교환신청 자세히 보기
    @GetMapping("/barterRequest/{barterRequestIdx}")
    public ResponseEntity<BarterRequestResponseDto> getBarterRequest(@PathVariable Long barterRequestIdx) {
        return ResponseEntity.ok()
                .body(barterRequestService.getBarterRequest(barterRequestIdx));
    }
    // 3. 자신의 물물 교환글 전체 보기
    @GetMapping("/barter/{barterIdx}")
    public ResponseEntity<List<BarterRequestResponseDto>> getBarterRequestByBarterIdx(@PathVariable Long barterIdx) {
        return ResponseEntity.ok()
                .body(barterRequestService.getBarterRequestListByBarterIdx(barterIdx));
    }


    // 구매자 입장

    //1. 교환 신청 등록
    @PostMapping("/{barterIdx}")
    public ResponseEntity<Void> addBarterRequest(@PathVariable Long barterIdx, @RequestBody BarterRequestRegistDto barterRequestRegistDto){
        barterRequestService.addBarterRequest(barterRequestRegistDto, barterIdx);
        return ResponseEntity.ok()
                .build();
    }
    //2. 자신의 교환 신청 목록 불러오기
    @GetMapping("/user/{userIdx}")
    public ResponseEntity<List<BarterRequestResponseDto>> getBarterRequestByUserIdx(@PathVariable Long userIdx){
        return ResponseEntity.ok()
                .body(barterRequestService.getBarterRequestListByRequester(userIdx));
    }
    //3. 자신의 교환 신청 삭제하기
    @DeleteMapping("/{barterRequestIdx}")
    public ResponseEntity<Void> deleteBarterRequest(@PathVariable Long barterRequestIdx) {
        barterRequestService.deleteBarterRequest(barterRequestIdx);
        return ResponseEntity.ok()
                .build();
    }

}
