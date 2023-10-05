package com.conseller.conseller.entity;

import com.conseller.conseller.inquiry.enums.InquiryStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "inquiryIdx")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(nullable = false)
    private String inquiryTitle;

    @Column(nullable = false)
    private String inquiryContent;

    @Column
    private String inquiryAnswer;

    @Column
    private LocalDateTime inquiryAnswerDate;

    @CreatedDate
    private LocalDateTime inquiryCreatedDate;

    @Column(nullable = false)
    private String inquiryStatus = InquiryStatus.CHECKING.getStatus();

    @Column(nullable = false)
    private Integer inquiryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_user")
    private User reportedUser;

}
