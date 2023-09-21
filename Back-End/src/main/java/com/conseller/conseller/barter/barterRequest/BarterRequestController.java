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

    @PostMapping({"", "/"})
    public ResponseEntity<Void> addBarterRequest(BarterRequestRegistDto barterRequestRegistDto){
        barterRequestService.addBarterRequest(barterRequestRegistDto);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{barterRequestIdx}")
    public ResponseEntity<Void> deleteBarterRequest(@PathVariable Long barterRequestIdx) {
        barterRequestService.deleteBarterRequest(barterRequestIdx);
        return ResponseEntity.ok()
                .build();
    }
}
