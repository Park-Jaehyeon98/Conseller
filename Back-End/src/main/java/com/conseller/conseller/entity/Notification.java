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
@EqualsAndHashCode(of = "notificationIdx")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationIdx;

    @Column(name = "notification_content", nullable = false)
    private String notificationContent;

    @CreatedDate
    private LocalDateTime notificationCreatedDate;

//    @Enumerated
//    private Enum notificationStatus;

    @Column(name = "seller", nullable = false)
    private Boolean seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

}
