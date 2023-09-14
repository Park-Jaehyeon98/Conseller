package com.conseller.conseller.auction.auction;

import com.conseller.conseller.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
