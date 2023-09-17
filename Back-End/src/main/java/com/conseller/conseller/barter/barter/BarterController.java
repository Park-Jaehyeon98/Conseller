package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
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


    @GetMapping({"", "/"})
    public ResponseEntity<List<BarterResponseDto>> getBarterList() {
        return ResponseEntity.ok()
                .body(barterService.getBarterList());
    }

    @GetMapping("/{barterIdx}")
    public ResponseEntity<BarterResponseDto> getBarter(@PathVariable Long barterIdx) {
        return ResponseEntity.ok()
                .body(barterService.getBarter(barterIdx));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Void> addBarter(@RequestBody BarterCreateDto barterCreateDto) {
        barterService.addBarter(barterCreateDto);
        return ResponseEntity.ok()
                .build();
    }

    @PatchMapping("/{barterIdx}")
    public ResponseEntity<Void> modifyBarter(@PathVariable Long barterIdx, @RequestBody BarterModifyRequestDto barterModifyRequestDto) {

        System.out.println(barterModifyRequestDto.toString());

        barterService.modifyBarter(barterIdx, barterModifyRequestDto);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/{barterIdx}")
    public ResponseEntity<Void> deleteBarter(@PathVariable Long barterIdx) {
        barterService.deleteBarter(barterIdx);
        return ResponseEntity.ok()
                .build();
    }
}
