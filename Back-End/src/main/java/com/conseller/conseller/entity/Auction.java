package com.conseller.conseller.entity;

import com.conseller.conseller.auction.auction.enums.AuctionStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    @ColumnDefault("0")
    private Integer auctionHighestBid;

    @Column(name = "auction_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuctionStatus auctionStatus;

    @CreatedDate
    private LocalDateTime auctionStartDate;

    @Column(name = "auction_end_date")
    private LocalDateTime auctionEndDate;

    @Column(name = "auction_completed_date")
    private LocalDateTime auctionCompletedDate;

    @OneToOne
    @JoinColumn(name = "gifticon_idx")
    private Gifticon gifticon;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;
}

