package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
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
        return new ResponseEntity<List<BarterResponseDto>>(barterService.getBarterList(), HttpStatus.OK);
    }

    @GetMapping("/{barterIdx}")
    public ResponseEntity<BarterResponseDto> getBarter(@RequestParam Long barterIdx) {
        BarterResponseDto barterReponseDto = barterService.getBarter(barterIdx);

        return new ResponseEntity<BarterResponseDto>(barterReponseDto, HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Void> addBarter(@RequestBody BarterCreateDto barterCreateDto) {
        return new ResponseEntity<Void>(barterService.addBarter(barterCreateDto), HttpStatus.OK);
    }
}
