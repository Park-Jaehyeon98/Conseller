package com.conseller.conseller.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "auctionBidIdx")
public class AuctionBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionBidIdx;

    @Column(name = "auction_bid_price", nullable = false)
    private Integer auctionBidPrice;

    @CreatedDate
    private LocalDateTime auctionRegistedDate;

//    @Column(name = "auction_bid_status", nullable = false)
//    private Enum auctionBidStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gifticon_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_idx")
    private Auction auction;

}
