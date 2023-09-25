package com.conseller.conseller.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "barterGuestItemIdx")
public class BarterGuestItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barterGuestItemIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barter_request_idx", nullable = false)
    private BarterRequest barterRequest;

    @OneToOne
    @JoinColumn(name = "gifticon_idx", nullable = false)
    private Gifticon gifticon;

    @Builder
    public BarterGuestItem(BarterRequest barterRequest, Gifticon gifticon) {
        this.barterRequest = barterRequest;
        this.gifticon = gifticon;
    }
}
