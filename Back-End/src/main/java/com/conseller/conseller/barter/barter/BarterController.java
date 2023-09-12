package com.conseller.conseller.barter.barter;

import com.conseller.conseller.barter.barter.barterDto.BarterCreateDto;
import com.conseller.conseller.barter.barter.barterDto.BarterResponseDto;
import com.conseller.conseller.entity.Barter;
import com.conseller.conseller.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/barter")
@RequiredArgsConstructor
public class BarterController {

    private final BarterService barterService;


    @GetMapping({"/", ""})
    public ResponseEntity<List<BarterResponseDto>> getBarterList() {
        return new ResponseEntity<List<BarterResponseDto>>(barterService.getBarterList(), HttpStatus.OK);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<Void> getBarter(BarterCreateDto barterCreateDto) {
        barterService.addBarter(barterCreateDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
