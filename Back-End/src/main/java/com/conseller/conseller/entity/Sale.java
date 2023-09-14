package com.conseller.conseller.entity;

import com.conseller.conseller.sale.enums.SaleStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "saleIdx")
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long saleIdx;

    @Column(name = "sale_price", nullable = false)
    private Integer salePrice;

    @CreatedDate
    private LocalDateTime saleCreatedDate;

    @Column(name = "sale_end_date")
    private LocalDateTime saleEndDate;

    @Column(name = "sale_text")
    private String saleText;

    @Column(name = "sale_status")
    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gifticon_idx")
    private Gifticon gifticon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

}
