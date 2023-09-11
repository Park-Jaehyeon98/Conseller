package com.conseller.conseller.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "barterIdx")
public class Barter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barterIdx;

    @Column(name = "barter_name", nullable = false)
    private String barterName;

    @Column(name = "barter_text", nullable = false)
    private String barterText;

    @CreatedDate
    private LocalDateTime barterCreatedDate;

    @Column(name = "barter_end_date", nullable = false)
    private LocalDateTime barterEndDate;

    @LastModifiedDate
    private LocalDateTime barterModifiedDate;

    @Column(name = "barter_completed_date")
    private LocalDateTime barterCompletedDate;

//    @Enumerated
//    private Enum barterStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User hostIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User barterCompleteGuestIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_idx")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_catergory_idx")
    private SubCategory preferSubCategory;
}

