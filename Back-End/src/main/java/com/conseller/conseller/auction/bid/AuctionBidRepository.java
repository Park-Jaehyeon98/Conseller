package com.conseller.conseller.auction.bid;

import com.conseller.conseller.entity.AuctionBid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionBidRepository extends JpaRepository<AuctionBid, Long> {
    List<AuctionBid> findByAuctionIdx(Long auctionIdx);
}
