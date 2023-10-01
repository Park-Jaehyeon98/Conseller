package com.conseller.conseller.entity;

import com.conseller.conseller.gifticon.dto.response.GifticonResponse;
import com.conseller.conseller.utils.DateTimeConverter;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter @Setter @ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "gifticonIdx")
public class Gifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gifticonIdx;

    @Column(name = "gifticon_barcode", nullable = false)
    private String gifticonBarcode;

    @Column(name = "gifticon_name", nullable = false)
    private String gifticonName;

    @CreatedDate
    @Column(name = "gifticon_start_date", nullable = false)
    private LocalDateTime gifticonStartDate;

    @Column(name = "gifticon_end_date", nullable = false)
    private LocalDateTime gifticonEndDate;

    /*
    원본이미지 : not null
    짜른 이미지 : null
     */
    @Column(name = "gifticon_all_image_url", nullable = false)
    private String gifticonAllImageUrl;

    @Column(name = "gifticon_data_image_url")
    private String gifticonDataImageUrl;

    @Column(name = "gifticon_status", nullable = false)
    private String gifticonStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_idx")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_idx")
    private MainCategory mainCategory;

    public GifticonResponse toResponseDto() {
        return GifticonResponse.builder()
                .gifticonIdx(this.gifticonIdx)
                .gifticonBarcode(this.gifticonBarcode)
                .gifticonName(this.gifticonName)
                .gifticonAllImageUrl(this.gifticonAllImageUrl)
                .gifticonDataImageUrl(this.gifticonDataImageUrl)
                .gifticonStartDate(DateTimeConverter.getInstance().convertString(this.gifticonStartDate))
                .gifticonEndDate(DateTimeConverter.getInstance().convertString(this.gifticonEndDate))
                .gifticonStatus(this.gifticonStatus)
                .userIdx(this.user.getUserIdx())
                .subCategoryIdx(this.subCategory.getSubCategoryIdx())
                .mainCategoryIdx(this.mainCategory.getMainCategoryIdx())
                .build();

    }
}
