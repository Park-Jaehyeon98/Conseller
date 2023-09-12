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
    @JoinColumn(name = "barter_idx", nullable = false)
    private Barter barter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;
}
