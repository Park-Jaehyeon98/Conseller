package com.conseller.conseller.entity;

import com.conseller.conseller.barter.BarterHostItem.BarterHostItemDto.BarterHostItemDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Builder
    public BarterHostItem(Barter barter, Gifticon gifticon) {
        this.barter = barter;
        this.gifticon = gifticon;
    }

    public BarterHostItemDto toBarterHostItemDto(Barter barter, Gifticon gifticon) {
        BarterHostItemDto barterHostItemDto = new BarterHostItemDto();
        barterHostItemDto.setBarter(barter);
        barterHostItemDto.setGifticon(gifticon);

        return barterHostItemDto;
    }
}
