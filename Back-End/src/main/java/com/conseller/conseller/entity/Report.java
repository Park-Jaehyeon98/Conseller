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
@EqualsAndHashCode(of = "reportIdx")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportIdx;

//    @Column(name = "report_category", nullable = false)
//    @Enumerated
//    private Enum reportCategory;

    @CreatedDate
    private LocalDateTime reportCreatedDate;

    @LastModifiedDate
    private LocalDateTime reportCompletedDate;

    @Column(name = "report_text", nullable = false)
    private String reportText;

//    @Column(name = "report_status", nullable = false)
//    private Enum reportStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_idx")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_idx")
    private User reported;

}
