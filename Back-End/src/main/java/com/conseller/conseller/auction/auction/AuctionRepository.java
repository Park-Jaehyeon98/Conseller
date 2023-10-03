package com.conseller.conseller.auction.auction;

import com.conseller.conseller.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    boolean existsByGifticon_GifticonIdx(Long gifticonIdx);

    @Query("select a from Auction a where a.auctionStatus = '거래 중' and current_timestamp - a.notificationCreatedDate >= 14 * 60 * 1000")
    List<Auction> findByAuctionListConfirm();

    @Query("select a from Auction a where a.auctionCompletedDate is null and a.auctionEndDate <= current_timestamp ")
    List<Auction> findAuctionAllExpired();

    @Query("select a from Auction a left join a.auctionBidList b where a.auctionStatus = '진행 중' group by a order by count(b) desc ")
    List<Auction> findAuctionList();

    @Query("select a from Auction a where a.auctionStatus = '낙찰'")
    List<Auction> findAwardedAuctionList();
}
