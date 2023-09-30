package com.conseller.conseller.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode(of = "usedGifticonIdx")
public class UsedGifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usedGifticonIdx;

    @Column(name = "used_gifticon_barcode")
    private String usedGifticonBarcode;

    @Column(name = "used_gifticon_date")
    private LocalDateTime usedGifticonDate;
}
