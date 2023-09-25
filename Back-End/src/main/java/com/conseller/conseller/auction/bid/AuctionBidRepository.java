package com.conseller.conseller.auction.bid;

import com.conseller.conseller.entity.AuctionBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionBidRepository extends JpaRepository<AuctionBid, Long> {
    @Query("select ab from AuctionBid ab where ab.auction.auctionIdx = :auctionIdx order by ab.auctionBidPrice desc")
    List<AuctionBid> findByAuctionIdxOrderByAuctionBidPriceDesc(Long auctionIdx);

    void deleteByUser_UserIdxAndAuction_AuctionIdx(Long userIdx, Long auctionIdx);
}
