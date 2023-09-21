package com.conseller.conseller.entity;

import com.conseller.conseller.store.enums.StoreStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "storeIdx")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeIdx;

    @Column(name = "store_price", nullable = false)
    private Integer storePrice;

    @CreatedDate
    private LocalDateTime storeCreatedDate;

    @Column(name = "store_end_date")
    private LocalDateTime storeEndDate;

    @Column(name = "store_text")
    private String storeText;

    @Column(name = "store_status")
    private String storeStatus = StoreStatus.IN_PROGRESS.getStatus();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gifticon_idx")
    private Gifticon gifticon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User consumer;

}
