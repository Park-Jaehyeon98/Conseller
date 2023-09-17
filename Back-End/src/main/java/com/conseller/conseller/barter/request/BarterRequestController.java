package com.conseller.conseller.barter.request;

import com.conseller.conseller.barter.request.barterRequestDto.BarterRequestRegistDto;
import com.conseller.conseller.barter.request.barterRequestDto.BarterRequestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/barter")
public class BarterRequestController {

    private final BarterRequestService barterRequestService;

    @GetMapping({"","/"})
    public ResponseEntity<List<BarterRequestResponseDto>> getBarterRequestList() {
        return ResponseEntity.ok()
                .body(barterRequestService.getBarterRequestList());
    }

    @GetMapping("/{barterRequestIdx}")
    public ResponseEntity<BarterRequestResponseDto> getBarterRequest(@PathVariable Long barterRequestIdx) {
        return ResponseEntity.ok()
                .body(barterRequestService.getBarterRequest(barterRequestIdx));
    }

    @GetMapping("/{barterIdx}")
    public ResponseEntity<List<BarterRequestResponseDto>> getBarterRequestByBarterIdx(@PathVariable Long barterIdx) {
        return ResponseEntity.ok()
                .body(barterRequestService.getBarterRequestListByBarterIdx(barterIdx));
    }

//    @PostMapping({"", "/"})
//    public ResponseEntity<Void> addBarterRequest(BarterRequestRegistDto barterRequestRegistDto){
//
//    }
}
