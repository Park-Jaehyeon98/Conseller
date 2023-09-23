package com.conseller.conseller.auction.auction;

import com.conseller.conseller.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    boolean existsByGifticon_GifticonIdx(Long gifticonIdx);
}
