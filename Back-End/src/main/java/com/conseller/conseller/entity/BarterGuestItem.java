package com.conseller.conseller.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
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

}
