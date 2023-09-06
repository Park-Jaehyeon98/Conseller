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
@EqualsAndHashCode(of = "inquiryIdx")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(nullable = false)
    private String inquiryName;

    @Column(nullable = false)
    private String inquiryText;

    @CreatedDate
    private LocalDateTime inquiryCreatedTime;

//    @Enumerated(EnumType.STRING)
//    private InquiryStatus inquiryStatus;

//    @Enumerated(EnumType.STRING)
//    private InquiryType inquiryType;


}
