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
@EqualsAndHashCode(of = "auctionIdx")
public class UsedGifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usedGifticonIdx;

    @Column(name = "used_gifticon_barcode")
    private String usedGifticonBarcode;

    @CreatedDate
    private LocalDateTime usedGifticonDate;
}
