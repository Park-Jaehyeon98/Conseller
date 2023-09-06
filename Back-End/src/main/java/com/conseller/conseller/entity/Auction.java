package com.conseller.conseller.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "auctionIdx")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionIdx;

    @Column(name = "auction_text", nullable = false)
    private String auctionText;

    @Column(name = "lower_price", nullable = false)
    private Integer lowerPrice;

    @Column(name = "upper_price", nullable = false)
    private Integer upperPrice;

    @Column(name = "auction_highest_bid", nullable = false)
    private Integer auctionHighestBid;

//    @Column(name = "auction_status", nullable = false)
//    private Enum auctionStatus;

    @CreatedDate
    private LocalDateTime auctionStartDate;

    @Column(name = "auction_end_date")
    private LocalDateTime auctionEndDate;

    @Column(name = "auction_completed_date")
    private LocalDateTime auctionCompletedDate;

    @OneToOne
    @JoinColumn(name = "gifticon_idx")
    private Gifticon gifticon;
}

