package com.conseller.conseller.auction.auction;

import com.conseller.conseller.auction.auction.dto.request.RegistAuctionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {
    @PostMapping("/regist")
    public ResponseEntity<Object> registAuction(@RequestBody RegistAuctionRequest request) {

        return ResponseEntity.ok()
                .build();
    }
}
