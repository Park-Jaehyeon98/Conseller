package com.conseller.conseller.auction.bid;

import com.conseller.conseller.entity.AuctionBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionBidRepository extends JpaRepository<AuctionBid, Long> {
}
