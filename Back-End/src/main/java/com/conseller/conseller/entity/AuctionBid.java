package com.conseller.conseller.entity;

import com.conseller.conseller.auction.bid.enums.BidStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "auctionBidIdx")
public class AuctionBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionBidIdx;

    @Column(name = "auction_bid_price", nullable = false)
    private Integer auctionBidPrice;

    @CreatedDate
    private LocalDateTime auctionRegistedDate;

    @Column(name = "auction_bid_status", nullable = false)
    private String auctionBidStatus = BidStatus.BIDED.getStatus();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gifticon_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_idx")
    private Auction auction;

}
