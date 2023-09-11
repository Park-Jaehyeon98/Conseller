package com.conseller.conseller.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "barterHostItemIdx")
public class BarterHostItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long barterHostItemIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barter_idx", nullable = false)
    private Barter barter;

    @OneToOne
    @JoinColumn(name = "gifticon_idx", nullable = false)
    private Gifticon gifticon;
}
