package com.conseller.conseller.entity;

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
@EqualsAndHashCode(of = "notificationIdx")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationIdx;

    @Column(name = "notification_title", nullable = false)
    private String notificationTitle;

    @Column(name = "notification_content", nullable = false)
    private String notificationContent;

    @CreatedDate
    private LocalDateTime notificationCreatedDate;

    @Column(name = "notification_type")
    private Integer notificationType;

    @Column(name = "seller", nullable = false)
    private Boolean seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

}
