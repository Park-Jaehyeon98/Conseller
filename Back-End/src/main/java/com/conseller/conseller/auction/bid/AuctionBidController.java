package com.conseller.conseller.auction.bid;

import com.conseller.conseller.auction.bid.dto.request.AuctionBidRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auction/bid")
public class AuctionBidController {
    private final AuctionBidService auctionBidService;

    // 입찰 등록
    @PostMapping("/{auction_idx}")
    public ResponseEntity<Object> registAuctionBid(@PathVariable("auction_idx") Long auctionIdx, @RequestBody AuctionBidRequest request) {
        auctionBidService.registAuctionBid(auctionIdx, request);

        return ResponseEntity.ok()
                .build();
    }

    // 입찰 삭제
    @DeleteMapping("/{auction_bid_idx}")
    public ResponseEntity<Object> deleteAuctionBid(@PathVariable("auction_bid_idx") Long auctionBidIdx) {
        auctionBidService.deleteAuctionBid(auctionBidIdx);

        return ResponseEntity.ok()
                .build();
    }

}
