package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.BarterModifyRequestDto;
import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/barter")
@RequiredArgsConstructor
public class BarterController {

    private final BarterService barterService;


    @GetMapping("/barter")
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
        barterService.modifyBarter(barterIdx, barterModifyRequestDto);
        return ResponseEntity.ok()
                .build();
    }
}
