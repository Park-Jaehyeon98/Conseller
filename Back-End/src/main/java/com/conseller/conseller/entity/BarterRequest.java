package com.conseller.conseller.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "barterRequestIdx")
public class BarterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barterRequestIdx;

//    @Enumerated
//    private Enum barterRequestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "barter_idx", nullable = false)
    private Barter barterIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_idx", nullable = false)
    private User userIdx;
}
